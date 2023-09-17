package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.repository.jpa.UserRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserDeletionService {
    private final UserRepository userRepository;

    private final JwtTokenService jwtTokenService;

    public void deleteUser(String token) {
        String userId = jwtTokenService.getData(token);
        userRepository.delete(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)));

    }
}
