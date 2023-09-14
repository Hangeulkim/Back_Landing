package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PhoneRequest {
    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
    private String phone;
}
