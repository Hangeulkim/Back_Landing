package osteam.backland.domain.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthMail is a Querydsl query type for AuthMail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthMail extends EntityPathBase<AuthMail> {

    private static final long serialVersionUID = -83422497L;

    public static final QAuthMail authMail = new QAuthMail("authMail");

    public final StringPath authCode = createString("authCode");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSuccess = createBoolean("isSuccess");

    public QAuthMail(String variable) {
        super(AuthMail.class, forVariable(variable));
    }

    public QAuthMail(Path<? extends AuthMail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthMail(PathMetadata metadata) {
        super(AuthMail.class, metadata);
    }

}

