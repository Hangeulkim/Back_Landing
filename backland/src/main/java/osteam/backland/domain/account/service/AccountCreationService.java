package osteam.backland.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.exception.PasswordDifFromConfirmException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;
import osteam.backland.global.attribute.Role;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountCreationService {
    private final AccountRepository accountRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public Account createUser(String userId, String pwd, String pwdConfirm, String name, String email) {
        if (!pwd.equals(pwdConfirm)) {
            throw new PasswordDifFromConfirmException();
        }

        String encodedPwd = passwordEncoder.encode(pwd);
        Account account = new Account(userId, encodedPwd, name, email, Role.ROLE_USER);

        Account save = accountRepository.save(account);

        return save;
    }
}
