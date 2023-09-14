package osteam.backland.domain.person.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import osteam.backland.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {
    @Id
    private Long id;

    @Setter
    @NotNull
    private String name;

    @NotNull
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
