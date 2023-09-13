package osteam.backland.domain.auth.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class MailServiceUnavailableException extends CommonException {
    public MailServiceUnavailableException() {

        super("MAIL_SERVICE_UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE, "메일 전송 서비스 점검중입니다.");
    }
}
