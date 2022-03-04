package wolox.training.exceptions;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static wolox.training.constants.ErrorMessages.BOOK_ALREADY_OWNED;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookAlreadyOwnedException extends  RuntimeException{
    public BookAlreadyOwnedException() {
        super(BOOK_ALREADY_OWNED);
    }
}
