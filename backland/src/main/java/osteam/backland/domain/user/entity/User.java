package osteam.backland.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.global.entity.PrimaryKeyEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends PrimaryKeyEntity {

    private String userName;
    private String password;
    private String userId;

    @OneToMany
    @JoinColumn
    private List<Person> people;
}
