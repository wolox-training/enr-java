package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static wolox.training.constants.ErrorMessages.MESSAGE_USER_MISMATCH;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserIdMismatchException extends RuntimeException {

    public UserIdMismatchException() {
        super(MESSAGE_USER_MISMATCH);
    }
}