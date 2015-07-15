package fbq.service;

import fbq.exception.EmptyQueueException;
import fbq.exception.UserNotFoundException;
import fbq.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Chris Campo
 */
@Component
public class QueueService {

    private final Queue<User> userQueue = new PriorityBlockingQueue<>();

    public User dequeue() {
        try {
            return userQueue.remove();
        } catch (final NoSuchElementException e) {
            throw new EmptyQueueException("Empty queue", e);
        }
    }

    public List<User> getUsers() {
        return new ArrayList<>(userQueue);
    }

    public User getUserById(final long id) {
        return userQueue.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public void addUser(final User user) {
        userQueue.add(user);
    }

    public void removeUserById(final long id) {
        userQueue.removeIf(u -> u.getId() == id);
    }
}
