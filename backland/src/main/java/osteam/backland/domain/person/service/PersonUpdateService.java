package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.jpa.PersonRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonUpdateService {
    private final PersonRepository personRepository;
    private final JwtTokenService jwtTokenService;

    public PersonDTO modifyPersonName(String token, String name, String phone) {
        String userId = jwtTokenService.getData(token);

        Person person = personRepository.searchPersonByUserIdAndPhone(userId, phone);

        person.setName(name);

        return new PersonDTO(name, phone);
    }
}
