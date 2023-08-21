package osteam.backland.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import osteam.backland.domain.person.entity.Person;
import osteam.backland.global.entity.PrimaryKeyEntity;
import osteam.backland.global.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends PrimaryKeyEntity implements UserDetails {

    @NotNull
    @Column(unique = true)
    private String userId;

    @NotNull
    private String userPwd;

    @NotNull
    private String userName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role = Role.ROLE_GUEST;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Person> people;

    public User(String userId, String userPwd, String userName) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
    }

    public Role setRole(Role role) {
        this.role = role;
        return this.role;
    }

    public List<Person> addPerson(Person person) {
        people.add(person);
        return people;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userPwd;
    }

    @Override
    public String getUsername() {
        return this.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
