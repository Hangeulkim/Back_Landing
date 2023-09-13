package osteam.backland.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.auth.entity.AuthMail;
import osteam.backland.domain.auth.repository.jpa.AuthMailRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthMailModificationService {
    private final AuthMailRepository authMailRepository;

    public AuthMail modifyIsSuccess(String email, String authCode) {
        AuthMail authMail = authMailRepository.findAuthMailByEmailAndAuthCode(email, authCode);

        authMail.setSuccess(true);
        authMail.setEndAt(LocalDateTime.now().plusMinutes(10));

        return authMail;
    }
}
