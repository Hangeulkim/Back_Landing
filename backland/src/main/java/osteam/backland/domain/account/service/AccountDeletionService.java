package osteam.backland.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.account.exception.UserNotFoundException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AccountDeletionService {
    private final AccountRepository accountRepository;

    private final JwtTokenService jwtTokenService;

    public void deleteUser(String token) {
        String userId = jwtTokenService.getData(token);
        accountRepository.delete(accountRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)));

    }
}
