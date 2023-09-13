package osteam.backland.domain.auth.repository.custom;

import osteam.backland.domain.auth.entity.AuthMail;

public interface AuthMailRepositoryCustom {

    AuthMail searchAuthMailByEmailAndIsSuccessFalse(String email);

    AuthMail searchAuthMailByEmailAndIsSuccessTrue(String email);
}
