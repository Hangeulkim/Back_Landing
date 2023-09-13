package osteam.backland.domain.person.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.person.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
