package osteam.backland.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.auth.entity.AuthMail;
import osteam.backland.domain.auth.repository.jpa.AuthMailRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMailCreateService {

    private final AuthMailRepository authMailRepository;

    //인증 메일을 보낸 적이 있는지 인증이 안된 코드를 찾는다.
    public String authCodeCreate(String email, String authCode) {
        AuthMail authMail = new AuthMail(email, authCode, LocalDateTime.now().plusMinutes(10));

        AuthMail save = authMailRepository.save(authMail);

        return save.getEmail();
    }

}
