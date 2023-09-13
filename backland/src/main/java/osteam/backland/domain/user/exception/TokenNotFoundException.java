package osteam.backland.domain.user.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class TokenNotFoundException extends CommonException {
    public TokenNotFoundException() {
        super("Token_NOT_FOUND_EXCEPTION", HttpStatus.NOT_FOUND, "RefreshToken not found");
    }
}
