package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import osteam.backland.domain.user.entity.User;
import osteam.backland.domain.user.exception.PasswordDifFromConfirmException;
import osteam.backland.domain.user.repository.jpa.UserRepository;
import osteam.backland.global.attribute.Role;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreationService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(String userId, String pwd, String pwdConfirm, String name, String email) {
        if (!pwd.equals(pwdConfirm)) {
            throw new PasswordDifFromConfirmException();
        }

        String encodedPwd = passwordEncoder.encode(pwd);
        User user = new User(userId, encodedPwd, name, email, Role.ROLE_USER);

        User save = userRepository.save(user);

        return save;
    }
}
