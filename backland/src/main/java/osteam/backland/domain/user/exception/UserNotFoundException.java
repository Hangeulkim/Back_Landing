package osteam.backland.domain.user.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class UserNotFoundException extends CommonException {
    public UserNotFoundException(String userId) {
        super("USER_NOT_FOUND_EXCEPTION", HttpStatus.NOT_FOUND, userId + " : not found");
    }
}
