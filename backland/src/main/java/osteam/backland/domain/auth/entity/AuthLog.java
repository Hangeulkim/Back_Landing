package osteam.backland.domain.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuthLog {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String ip;

    public AuthLog(String email, String ip) {
        this.email = email;
        this.ip = ip;
    }
}
