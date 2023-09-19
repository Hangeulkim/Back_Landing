package osteam.backland.domain.person.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.GraphQlRepository;
import osteam.backland.domain.account.entity.QAccount;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.domain.person.entity.QPerson;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.custom.PersonRepositoryCustom;

import java.util.HashSet;
import java.util.Set;

@GraphQlRepository
public class PersonRepositoryImpl implements PersonRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QPerson person;
    private final QAccount account;

    @Autowired
    public PersonRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
        this.person = QPerson.person;
        this.account = QAccount.account;
    }

    @Override
    public Person searchPersonByUserIdAndPhone(String userId, String phone) {
        return queryFactory.selectFrom(person)
                .join(person.account, account)
                .fetchJoin()
                .where(account.id.eq(userId), person.phone.eq(phone))
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
                        .join(person.account, account)
                        .where(account.id.eq(userId), nameContain(name), phoneContain(phone))
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
