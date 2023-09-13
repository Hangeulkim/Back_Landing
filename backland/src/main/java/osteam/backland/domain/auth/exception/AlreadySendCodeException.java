package osteam.backland.domain.auth.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class AlreadySendCodeException extends CommonException {
    public AlreadySendCodeException() {

        super("ALREADY_SEND_CODE", HttpStatus.FORBIDDEN, "이메일 인증 코드 대기 시간 입니다.");
    }
}
