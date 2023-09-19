package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.exception.UserNotFoundException;
import osteam.backland.domain.account.repository.jpa.AccountRepository;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.global.security.service.JwtTokenService;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PersonCreateService {
    private final AccountRepository accountRepository;
    private final JwtTokenService jwtTokenService;

    public PersonDTO createPerson(String token, String name, String phone) {
        String userId = jwtTokenService.getData(token);
        Account account = accountRepository.findAccountById(userId);

        if (account == null) {
            throw new UserNotFoundException(userId);
        }

        Person person = new Person(name, phone);
        account.addPerson(person);
        person.setAccount(account);

        return new PersonDTO(name, phone);
    }
}
