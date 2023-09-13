package osteam.backland.domain.auth.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.auth.entity.AuthMail;
import osteam.backland.domain.auth.repository.custom.AuthMailRepositoryCustom;

@Repository
public interface AuthMailRepository extends JpaRepository<AuthMail, Long>, AuthMailRepositoryCustom {
    
    AuthMail findAuthMailByEmailAndAuthCode(String email, String authCode);
}
