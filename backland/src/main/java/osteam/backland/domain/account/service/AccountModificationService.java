package osteam.backland.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.exception.UserNotFoundException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountModificationService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Account modifyUser(String userId, String email, String name) {
        Account account = accountRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        account.setId(userId);
        account.setEmail(email);
        account.setName(name);

        return account;
    }

    public Account modifyUserPwd(String userId, String pwd) {
        Account account = accountRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );

        String encodedPwd = passwordEncoder.encode(pwd);
        account.setPwd(encodedPwd);

        return account;
    }
}
