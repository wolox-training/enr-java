package wolox.training.exceptions;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static wolox.training.constants.ErrorMessages.USER_NOT_FOUND;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
