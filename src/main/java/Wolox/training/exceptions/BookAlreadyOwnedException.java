package wolox.training.exceptions;
        import java.lang.RuntimeException;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookAlreadyOwnedException extends  RuntimeException {

    private static final String MESSAGE_ALREADY_OWNED = "You already have this associated book";

    public BookAlreadyOwnedException() {
        super(MESSAGE_ALREADY_OWNED);
    }
}
