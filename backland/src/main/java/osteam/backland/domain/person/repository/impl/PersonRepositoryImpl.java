package osteam.backland.domain.person.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.GraphQlRepository;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.QPerson;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.custom.PersonRepositoryCustom;
import osteam.backland.domain.user.entity.QUser;

import java.util.HashSet;
import java.util.Set;

@GraphQlRepository
public class PersonRepositoryImpl implements PersonRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QPerson person;
    private final QUser user;

    @Autowired
    public PersonRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
        this.person = QPerson.person;
        this.user = QUser.user;
    }

    @Override
    public Person searchPersonByUserIdAndPhone(String userId, String phone) {
        return queryFactory.selectFrom(person)
                .join(person.user, user)
                .fetchJoin()
                .where(user.id.eq(userId), person.phone.eq(phone))
                .fetchOne();
    }

    @Override
    public Set<PersonDTO> searchPeople(String userId, String name, String phone) {
        return new HashSet<>(
                queryFactory.select(
                                Projections.constructor(
                                        PersonDTO.class,
                                        person.name,
                                        person.phone
                                )
                        ).from(person)
                        .join(person.user, user)
                        .where(user.id.eq(userId), nameContain(name), phoneContain(phone))
                        .fetch());
    }

    public BooleanExpression nameContain(String name) {
        if (name == null)
            return null;
        return person.name.contains(name);
    }

    public BooleanExpression phoneContain(String phone) {
        if (phone == null)
            return null;
        return person.phone.contains(phone);
    }
}
