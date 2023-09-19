package osteam.backland.domain.person.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import osteam.backland.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Setter
    @NotNull
    private String name;

    @NotNull
    private String phone;

    @ManyToOne
    @Setter
    @JoinColumn(name = "user_id")
    private User user;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
