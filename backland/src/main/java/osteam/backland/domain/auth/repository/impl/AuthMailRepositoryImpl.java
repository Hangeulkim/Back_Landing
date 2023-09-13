package osteam.backland.domain.auth.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import osteam.backland.domain.auth.entity.AuthMail;
import osteam.backland.domain.auth.entity.QAuthMail;
import osteam.backland.domain.auth.repository.custom.AuthMailRepositoryCustom;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AuthMailRepositoryImpl implements AuthMailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final QAuthMail authMail = QAuthMail.authMail;

    @Override
    public AuthMail searchAuthMailByEmailAndIsSuccessFalse(String email) {
        return queryFactory
                .selectFrom(authMail)
                .where(authMail.endAt.goe(LocalDateTime.now()), authMail.email.eq(email), authMail.isSuccess.eq(false))
                .fetchOne();
    }

    @Override
    public AuthMail searchAuthMailByEmailAndIsSuccessTrue(String email) {
        return queryFactory
                .selectFrom(authMail)
                .where(authMail.endAt.goe(LocalDateTime.now()), authMail.email.eq(email), authMail.isSuccess.eq(true))
                .fetchOne();
    }
}
