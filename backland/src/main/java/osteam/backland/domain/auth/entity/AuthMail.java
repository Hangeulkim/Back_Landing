package osteam.backland.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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
    private Long id;

    public AuthMail(String authCode, LocalDateTime endAt, String email) {
        this.authCode = authCode;
        this.endAt = endAt;
        this.email = email;
        this.isSuccess = false;
    }
}
