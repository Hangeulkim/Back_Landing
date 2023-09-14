package osteam.backland.domain.person.entity.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import osteam.backland.domain.person.controller.response.PhoneResponse;

@Data
@NoArgsConstructor
public class PersonDTO {
    private String name;
    private String phone;

    @QueryProjection
    public PersonDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public PhoneResponse toResponse() {
        return new PhoneResponse(this.name, this.phone);
    }
}
