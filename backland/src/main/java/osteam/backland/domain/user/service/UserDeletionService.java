package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.repository.jpa.UserRepository;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserDeletionService {
    private final UserRepository userRepository;

    public void deleteUser(String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)));

    }
}
