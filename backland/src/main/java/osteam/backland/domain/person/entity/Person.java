package osteam.backland.domain.person.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import osteam.backland.domain.user.entity.User;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person extends PrimaryKeyEntity {
    private String name;
    private String phone;

    @ManyToOne
    @JoinColumn
    private User user;
}
