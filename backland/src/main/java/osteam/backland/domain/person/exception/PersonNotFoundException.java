package osteam.backland.domain.person.exception;

import org.springframework.http.HttpStatus;
import osteam.backland.global.exception.exception.CommonException;

public class PersonNotFoundException extends CommonException {
    public PersonNotFoundException(String phone) {
        super("PERSON_NOT_FOUND_EXCEPTION", HttpStatus.NOT_FOUND, phone + " : not found");
    }
}
