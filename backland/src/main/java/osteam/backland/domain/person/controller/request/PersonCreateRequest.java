package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateRequest {
    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]+$", message = "Phone 번호를 입력하세요")
    private String phone;
}
