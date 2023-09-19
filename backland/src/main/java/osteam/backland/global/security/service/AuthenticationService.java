package osteam.backland.global.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.exception.UserNotFoundException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Account account = accountRepository.findUserById(userId);
        if (account == null) {
            throw new UserNotFoundException(userId);
        }
        return account;
    }

    public String getUserIdByAuthentication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Account)
            return ((Account) principal).getId();
        return null;
    }

    public String getUserNameByAuthentication() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Account)
            return ((Account) principal).getUsername();
        return null;
    }
}
