package ccampo133.fbaas.service;

import ccampo133.fbaas.exception.AlreadyInQueueException;
import ccampo133.fbaas.exception.EmptyQueueException;
import ccampo133.fbaas.exception.UserNotFoundException;
import ccampo133.fbaas.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Chris Campo
 */
@Service
public class QueueService {

    private static int DEFAULT_INITIAL_CAPACITY = 11;

    private final Queue<User> queue = new PriorityBlockingQueue<>(DEFAULT_INITIAL_CAPACITY, new UserComparator());

    // We keep track of the users IDs in a separate set to avoid having to linearly search
    // the priority queue (heap) every time to keep insertion roughly O(1) instead of O(n).
    private final Set<Long> ids = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public User dequeue() {
        try {
            final User user = queue.remove();
            ids.remove(user.getId());
            return user;
        } catch (final NoSuchElementException e) {
            throw new EmptyQueueException("Empty queue", e);
        }
    }

    public User[] getUsers() {
        final User[] users = queue.toArray(new User[queue.size()]);
        Arrays.sort(users);
        return users;
    }

    public int getUserPositionById(final long id) {
        final User[] sortedUsers = getUsers();
        for (int i = 0; i < sortedUsers.length; ++i) {
            if (sortedUsers[i].getId() == id) {
                return i;
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found");
    }

    public User getUserById(final long id) {
        return queue.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public void addUser(@NotNull final User user) {
        if (!ids.contains(user.getId())) {
            queue.add(user);
            ids.add(user.getId());
        } else {
            throw new AlreadyInQueueException("User with ID " + user.getId() + " is already in the queue");
        }
    }

    public void removeUserById(final long id) {
        if (!ids.contains(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        queue.removeIf(u -> u.getId() == id);
        ids.remove(id);
    }

    public double getAverageWaitTime(final long timestamp) {
        // Technically filter isn't required here, but it gets rid of funny values
        // (e.g. negative average times) when you input a timestamp in the past.
        return queue.stream()
                .filter(u -> u.getTimestamp() <= timestamp)
                .mapToLong(u -> timestamp - u.getTimestamp())
                .average().getAsDouble();
    }

    class UserComparator implements Comparator<User> {
        @Override
        public int compare(@NotNull final User user1, @NotNull final User user2) {
            final long now = System.currentTimeMillis();
            if (user1.isManagement() && user2.isManagement()) {
                return Long.compare(user2.getPriority(now), user1.getPriority(now));
            } else if (user1.isManagement()) {
                return -1;
            } else if (user2.isManagement()) {
                return 1;
            } else {
                return Long.compare(user2.getPriority(now), user1.getPriority(now));
            }
        }
    }
}
