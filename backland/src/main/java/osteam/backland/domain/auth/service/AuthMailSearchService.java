package osteam.backland.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.auth.entity.AuthMail;
import osteam.backland.domain.auth.exception.AlreadySendCodeException;
import osteam.backland.domain.auth.exception.CodeDifException;
import osteam.backland.domain.auth.exception.CodeNotFoundException;
import osteam.backland.domain.auth.repository.jpa.AuthMailRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthMailSearchService {

    private final AuthMailRepository authMailRepository;

    //인증 메일을 보낸 적이 있는지 인증이 안된 코드를 찾는다.
    public String authCodeSearch(String email) {
        AuthMail authMail = authMailRepository.searchAuthMailByEmailAndIsSuccessFalse(email);

        if (authMail == null)
            return null;

        return authMail.getAuthCode();
    }

    public String authCodeSearchSuccess(String email) {
        AuthMail authMail = authMailRepository.searchAuthMailByEmailAndIsSuccessTrue(email);

        if (authMail == null) {
            throw new CodeNotFoundException();
        }

        return authMail.getAuthCode();
    }

    public String authCodeExist(String email) {
        String authCode = authCodeSearch(email);

        if (authCode == null) {
            throw new CodeNotFoundException();
        }

        return authCode;
    }

    public boolean authCodeNotExist(String email) {
        String authCode = authCodeSearch(email);

        if (authCode != null) {
            throw new AlreadySendCodeException();
        }

        return true;
    }

    public boolean authCodeSameWithInputCode(String email, String authCode) {
        String authCodeSearch = authCodeExist(email);

        if (!authCodeSearch.equals(authCode)) {
            throw new CodeDifException();
        }

        return true;
    }
}
