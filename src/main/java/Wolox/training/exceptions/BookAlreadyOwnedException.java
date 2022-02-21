package Wolox.training.exceptions;
        import java.lang.RuntimeException;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BookAlreadyOwnedException extends  RuntimeException{
    public BookAlreadyOwnedException() {
        super("You already have this associated book");
    }
}
