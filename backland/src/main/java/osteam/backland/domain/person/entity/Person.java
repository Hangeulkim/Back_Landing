package osteam.backland.domain.person.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import osteam.backland.domain.account.entity.Account;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long id;

    @Setter
    @NotNull
    private String name;

    @NotNull
    private String phone;

    @ManyToOne
    @Setter
    @JoinColumn(name = "account_id")
    private Account account;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
