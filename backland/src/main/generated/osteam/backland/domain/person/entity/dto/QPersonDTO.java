package osteam.backland.domain.person.entity.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * osteam.backland.domain.person.entity.dto.QPersonDTO is a Querydsl Projection type for PersonDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPersonDTO extends ConstructorExpression<PersonDTO> {

    private static final long serialVersionUID = 1443303630L;

    public QPersonDTO(com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> phone) {
        super(PersonDTO.class, new Class<?>[]{String.class, String.class}, name, phone);
    }

}

