package osteam.backland.domain.person.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteRequest {
    @NotNull
    public String phone;
}
