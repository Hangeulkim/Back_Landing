package osteam.backland.domain.account.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    @Schema(description = "액세스 토큰")
    @NotNull
    private String accessToken;

    @Schema(description = "리프레시 토큰")
    @NotNull
    private String refreshToken;
}
