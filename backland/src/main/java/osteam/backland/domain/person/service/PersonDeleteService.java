package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.exception.PersonNotFoundException;
import osteam.backland.domain.person.repository.jpa.PersonRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Service
@RequiredArgsConstructor
public class PersonDeleteService {
    private final PersonRepository personRepository;

    private final JwtTokenService jwtTokenService;

    public long deletePerson(String token, String phone) {
        String userId = jwtTokenService.getData(token);

        Person person = personRepository.findByPhone(phone);

        if (person == null) {
            throw new PersonNotFoundException(phone);
        }

        personRepository.delete(person);
        return person.getId();
    }
}
