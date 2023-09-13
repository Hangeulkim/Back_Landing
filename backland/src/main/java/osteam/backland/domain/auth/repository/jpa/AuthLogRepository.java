package osteam.backland.domain.auth.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.auth.entity.AuthLog;

@Repository
public interface AuthLogRepository extends JpaRepository<AuthLog, Long> {
}
