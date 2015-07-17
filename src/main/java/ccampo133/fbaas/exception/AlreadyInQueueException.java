package ccampo133.fbaas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chris Campo
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyInQueueException extends RuntimeException {

    public AlreadyInQueueException(final String message) {
        super(message);
    }
}
