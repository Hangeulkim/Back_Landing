package osteam.backland.domain.auth.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class CodeNotFoundException extends CommonException {
    public CodeNotFoundException() {
        super("CODE_NOT_FOUND", HttpStatus.FORBIDDEN, "유효 시간 내의 인증 코드를 찾을 수 없습니다.");
    }
}
