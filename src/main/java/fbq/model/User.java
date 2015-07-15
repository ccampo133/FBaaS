package fbq.model;

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
        if (this.isFizz()) {
            return (long) (dt * Math.log(dt));
        } else if (this.isBuzz()) {
            return (long) (2 * dt * Math.log(dt));
        } else {
            return dt;
        }
    }

    boolean isFizz() {
        return id % 3 == 0;
    }

    boolean isBuzz() {
        return id % 5 == 0;
    }

    boolean isFizzBuzz() {
        return isFizz() && isBuzz();
    }

    @Override
    public int compareTo(final User other) {
        final long now = System.currentTimeMillis();
        if (this.isFizzBuzz() && other.isFizzBuzz()) {
            return Long.compare(other.getPriority(now), this.getPriority(now));
        } else if (this.isFizzBuzz()) {
            return -1;
        } else if (other.isFizzBuzz()) {
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
