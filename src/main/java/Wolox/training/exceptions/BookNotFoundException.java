package wolox.training.exceptions;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static wolox.training.constants.ErrorMessages.BOOK_NOT_FOUND;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Book not found")
public class BookNotFoundException extends  RuntimeException{
    public BookNotFoundException() {
        super(BOOK_NOT_FOUND);
    }
}
