package osteam.backland.domain.person.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.repository.custom.PersonRepositoryCustom;

@GraphQlRepository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryCustom {
    
}
