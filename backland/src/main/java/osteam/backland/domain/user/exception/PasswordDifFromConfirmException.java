package osteam.backland.domain.user.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class PasswordDifFromConfirmException extends CommonException {
    public PasswordDifFromConfirmException() {
        super("CONFIRM_PASSWORD_NOT_SAME", HttpStatus.FORBIDDEN, "비밀번호와 확인 비밀번호가 일치 하지 않습니다.");
    }
}
