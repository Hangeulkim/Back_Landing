package osteam.backland.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.exception.PasswordMissMatchException;
import osteam.backland.domain.account.exception.UserAlreadyExistException;
import osteam.backland.domain.account.exception.UserNotFoundException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountValidationService {
    private final AccountRepository accountRepository;

    private final PasswordEncoder pe;

    public boolean userNameOrEmailExist(String userName, String email) {
        Account account = accountRepository.findAccountByEmailOrName(email, userName);

        if (account != null) {
            throw new UserAlreadyExistException();
        }

        return true;
    }

    public boolean userValidate(String id, String pwd) {
        Account account = accountRepository.findAccountById(id);

        if (account == null) {
            throw new UserNotFoundException(id);
        }

        if (!pe.matches(pwd, account.getPwd())) {
            throw new PasswordMissMatchException();
        }

        return true;
    }
}
