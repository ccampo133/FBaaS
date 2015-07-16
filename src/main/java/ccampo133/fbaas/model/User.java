package ccampo133.fbaas.model;

/**
 * @author Chris Campo
 */
public class User {
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

    public long getPriority(final long now) {
        final long dt = now - this.getTimestamp();
        if (this.isPriorityUser()) {
            return Math.max(3, (long) (dt * Math.log(dt)));
        } else if (this.isVIP()) {
            return Math.max(4, (long) (2 * dt * Math.log(dt)));
        } else {
            return dt;
        }
    }

    public boolean isPriorityUser() {
        return id % 3 == 0;
    }

    public boolean isVIP() {
        return id % 5 == 0;
    }

    public boolean isManagement() {
        return isPriorityUser() && isVIP();
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
