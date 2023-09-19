package osteam.backland.domain.account.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 2029783038L;

    public static final QAccount account = new QAccount("account");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final BooleanPath emailVerified = createBoolean("emailVerified");

    public final StringPath id = createString("id");

    public final BooleanPath locked = createBoolean("locked");

    public final StringPath name = createString("name");

    public final ListPath<osteam.backland.domain.person.entity.Person, osteam.backland.domain.person.entity.QPerson> people = this.<osteam.backland.domain.person.entity.Person, osteam.backland.domain.person.entity.QPerson>createList("people", osteam.backland.domain.person.entity.Person.class, osteam.backland.domain.person.entity.QPerson.class, PathInits.DIRECT2);

    public final StringPath pwd = createString("pwd");

    public final EnumPath<osteam.backland.global.attribute.Role> role = createEnum("role", osteam.backland.global.attribute.Role.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QAccount(String variable) {
        super(Account.class, forVariable(variable));
    }

    public QAccount(Path<? extends Account> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount(PathMetadata metadata) {
        super(Account.class, metadata);
    }

}

