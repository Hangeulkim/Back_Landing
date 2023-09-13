package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.user.entity.User;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.repository.jpa.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserModificationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User modifyUser(String userId, String email, String name) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        user.setId(userId);
        user.setEmail(email);
        user.setName(name);

        return user;
    }

    public User modifyUserPwd(String userId, String pwd) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        String encodedPwd = passwordEncoder.encode(pwd);
        user.setPwd(encodedPwd);

        return user;
    }
}
