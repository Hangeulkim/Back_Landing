package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import osteam.backland.domain.user.entity.User;
import osteam.backland.domain.user.exception.PasswordMissMatchException;
import osteam.backland.domain.user.exception.UserAlreadyExistException;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.repository.jpa.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder pe;

    public boolean userNameOrEmailExist(String userName, String email) {
        User user = userRepository.findUserByEmailOrName(email, userName);

        if (user != null) {
            throw new UserAlreadyExistException();
        }

        return true;
    }

    public boolean userValidate(String id, String pwd) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        if (!pe.matches(pwd, user.getPwd())) {
            throw new PasswordMissMatchException();
        }

        return true;
    }
}
