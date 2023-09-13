package osteam.backland.domain.user.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class UserAlreadyExistException extends CommonException {
    public UserAlreadyExistException() {
        super("USER_ALREADY_EXIST_EXCEPTION", HttpStatus.FORBIDDEN, "저장 하려는 데이터 중 이미 있는 데이터와 중복되는 값이 존재 합니다.");
    }
}
