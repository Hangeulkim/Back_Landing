package osteam.backland.domain.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthLog is a Querydsl query type for AuthLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthLog extends EntityPathBase<AuthLog> {

    private static final long serialVersionUID = 828592412L;

    public static final QAuthLog authLog = new QAuthLog("authLog");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public QAuthLog(String variable) {
        super(AuthLog.class, forVariable(variable));
    }

    public QAuthLog(Path<? extends AuthLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthLog(PathMetadata metadata) {
        super(AuthLog.class, metadata);
    }

}

