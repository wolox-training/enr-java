package wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static wolox.training.constants.ErrorMessages.MESSAGE_BOOK_MISMATCH;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BookIdMismatchException extends RuntimeException {

    public BookIdMismatchException() {
        super(MESSAGE_BOOK_MISMATCH);
    }
}