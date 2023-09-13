package osteam.backland.domain.auth.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class CodeDifException extends CommonException {
    public CodeDifException() {
        super("CODE_DIFFERENCE", HttpStatus.FORBIDDEN, "인증 코드가 틀립니다.");
    }
}
