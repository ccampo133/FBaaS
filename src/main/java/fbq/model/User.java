package fbq.model;

import org.jetbrains.annotations.NotNull;

/**
 * @author Chris Campo
 */
public class User implements Comparable<User> {
    private final long id;
    private final long timestamp;

    public User(final long id, final long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    long getPriority(final long now) {
        final long dt = now - this.getTimestamp();
        if (this.isPriority()) {
            return Math.max(3, (long) (dt * Math.log(dt)));
        } else if (this.isVIP()) {
            return Math.max(4, (long) (2 * dt * Math.log(dt)));
        } else {
            return dt;
        }
    }

    boolean isPriority() {
        return id % 3 == 0;
    }

    boolean isVIP() {
        return id % 5 == 0;
    }

    boolean isManagement() {
        return isPriority() && isVIP();
    }

    // Have to do compareTo backwards here because Java's priority
    // queue is based on a min heap, and we need a max heap.
    @Override
    public int compareTo(@NotNull final User other) {
        final long now = System.currentTimeMillis();
        if (this.isManagement() && other.isManagement()) {
            return Long.compare(other.getPriority(now), this.getPriority(now));
        } else if (this.isManagement()) {
            return -1;
        } else if (other.isManagement()) {
            return 1;
        } else {
            return Long.compare(other.getPriority(now), this.getPriority(now));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        final User user = (User) o;
        return id == user.id && timestamp == user.timestamp;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
