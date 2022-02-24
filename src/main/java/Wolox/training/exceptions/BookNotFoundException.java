package Wolox.training.exceptions;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends  RuntimeException {

    private static final String MESSAGE_NOT_FOUND  = "Book not found";

    public BookNotFoundException() {
        super(MESSAGE_NOT_FOUND);
    }
}
