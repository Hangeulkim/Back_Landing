package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.user.entity.User;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.repository.jpa.UserRepository;
import osteam.backland.global.security.service.JwtTokenService;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PersonCreateService {
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public PersonDTO createPerson(String token, String name, String phone) {
        String userId = jwtTokenService.getData(token);
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        Person person = new Person(name, phone);
        user.addPerson(person);
        person.setUser(user);

        return new PersonDTO(name, phone);
    }
}
