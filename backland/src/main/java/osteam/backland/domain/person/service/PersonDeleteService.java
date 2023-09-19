package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.exception.PersonNotFoundException;
import osteam.backland.domain.person.repository.jpa.PersonRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PersonDeleteService {
    private final PersonRepository personRepository;

    private final JwtTokenService jwtTokenService;

    public long deletePerson(String token, String phone) {
        String userId = jwtTokenService.getData(token);

        Person person = personRepository.searchPersonByUserIdAndPhone(userId, phone);

        if (person == null) {
            throw new PersonNotFoundException(phone);
        }

        personRepository.delete(person);
        return person.getId();
    }
}
