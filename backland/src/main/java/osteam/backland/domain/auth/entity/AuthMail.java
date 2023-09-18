package osteam.backland.domain.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuthMail {

    @NotNull
    @Column(length = 6)
    private String authCode;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Setter
    private LocalDateTime endAt;

    @NotNull
    private String email;

    @NotNull
    @Setter
    private boolean isSuccess;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    public AuthMail(String email, String authCode, LocalDateTime endAt) {
        this.authCode = authCode;
        this.endAt = endAt;
        this.email = email;
        this.isSuccess = false;
    }
}
