package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookIdMismatchException extends RuntimeException {

    private static final String MESSAGE_MISMATCH  = "Book mismatch.";

    public BookIdMismatchException() {
        super(MESSAGE_MISMATCH);
    }
}
