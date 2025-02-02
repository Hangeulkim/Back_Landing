package osteam.backland.global.exception.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private String message;
}
