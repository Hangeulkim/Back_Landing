package osteam.backland.domain.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.user.entity.User;
import osteam.backland.domain.user.repository.custom.UserRepositoryCustom;


@Repository
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {

    User findUserByEmailOrName(String email, String name);

    User findUserById(String id);
}
