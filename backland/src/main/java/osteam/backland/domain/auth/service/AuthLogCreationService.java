package osteam.backland.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.auth.entity.AuthLog;
import osteam.backland.domain.auth.repository.jpa.AuthLogRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthLogCreationService {

    private final AuthLogRepository authLogRepository;

    public AuthLog createAuthLog(String email, String ip) {
        AuthLog authLog = new AuthLog(email, ip);

        AuthLog save = authLogRepository.save(authLog);

        return save;
    }
}
