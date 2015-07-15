package fbq.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chris Campo
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
