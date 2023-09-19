package osteam.backland.domain.account.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.account.entity.Account;
import osteam.backland.domain.account.repository.custom.AccountRepositoryCustom;


@Repository
public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {

    Account findUserByEmailOrName(String email, String name);

    Account findUserById(String id);
}
