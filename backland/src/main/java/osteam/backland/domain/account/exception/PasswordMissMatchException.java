package osteam.backland.domain.account.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class PasswordMissMatchException extends CommonException {
    public PasswordMissMatchException() {
        super("PASSWORD_MISS_MATCH", HttpStatus.FORBIDDEN, "비밀번호 혹은 아이디가 잘못 되었습니다.");
    }
}
