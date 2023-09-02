package osteam.backland.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 280976590L;

    public static final QUser user = new QUser("user");

    public final osteam.backland.global.entity.QPrimaryKeyEntity _super = new osteam.backland.global.entity.QPrimaryKeyEntity(this);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final ListPath<osteam.backland.domain.person.entity.Person, osteam.backland.domain.person.entity.QPerson> people = this.<osteam.backland.domain.person.entity.Person, osteam.backland.domain.person.entity.QPerson>createList("people", osteam.backland.domain.person.entity.Person.class, osteam.backland.domain.person.entity.QPerson.class, PathInits.DIRECT2);

    public final EnumPath<osteam.backland.global.entity.Role> role = createEnum("role", osteam.backland.global.entity.Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userPwd = createString("userPwd");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

