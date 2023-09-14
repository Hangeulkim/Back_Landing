package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.controller.response.PhoneResponse;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.jpa.PersonRepository;
import osteam.backland.global.security.service.JwtTokenService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSearchService {

    private final PersonRepository personRepository;
    private final JwtTokenService jwtTokenService;

    public PersonDTO searchPerson(String auth, String phone) {
        String id = jwtTokenService.getData(auth);

        Person person = personRepository.searchPersonByUserIdAndPhone(id, phone);
        if (person == null) {
            return null;
        }

        return new PersonDTO(person.getName(), person.getPhone());
    }

    public Set<PhoneResponse> searchPeople(String auth, String name, String phone) {
        String id = jwtTokenService.getData(auth);

        Set<PersonDTO> peopleDTO = personRepository.searchPeople(id, name, phone);

        Set<PhoneResponse> peopleResponse = peopleDTO.stream().map(PersonDTO::toResponse).collect(Collectors.toSet());

        return peopleResponse;
    }
}
