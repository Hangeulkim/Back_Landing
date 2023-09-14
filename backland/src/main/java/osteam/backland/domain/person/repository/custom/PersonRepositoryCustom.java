package osteam.backland.domain.person.repository.custom;

import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.dto.PersonDTO;

import java.util.Set;

public interface PersonRepositoryCustom {
    Person searchPersonByUserIdAndPhone(String userId, String phone);

    Set<PersonDTO> searchPeople(String userId, String name, String phone);
}
