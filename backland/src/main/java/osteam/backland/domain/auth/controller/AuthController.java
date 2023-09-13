package osteam.backland.domain.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import osteam.backland.domain.auth.controller.request.AuthCodeRequest;
import osteam.backland.domain.auth.controller.request.AuthMailRequest;
import osteam.backland.domain.auth.exception.AlreadySendCodeException;
import osteam.backland.domain.auth.exception.CodeDifException;
import osteam.backland.domain.auth.exception.MailServiceUnavailableException;
import osteam.backland.domain.auth.service.AuthLogCreationService;
import osteam.backland.domain.auth.service.AuthMailModificationService;
import osteam.backland.domain.auth.service.AuthMailSearchService;
import osteam.backland.domain.auth.service.MailService;
import osteam.backland.domain.user.controller.request.TokenRequest;
import osteam.backland.domain.user.controller.response.TokenResponse;
import osteam.backland.domain.user.exception.TokenNotFoundException;
import osteam.backland.domain.user.service.TokenCreationService;
import osteam.backland.domain.user.service.TokenDeletionService;
import osteam.backland.domain.user.service.TokenValidationService;
import osteam.backland.global.security.response.RefreshTokenResponse;
import osteam.backland.global.security.service.IpSearchService;

@Tag(name = "인증", description = "인증 관련 api")
@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthMailSearchService authMailSearchService;
    private final AuthMailModificationService authMailModificationService;
    private final AuthLogCreationService authLogCreationService;

    private final TokenDeletionService tokenDeletionService;
    private final TokenValidationService tokenValidationService;

    private final TokenCreationService tokenCreationService;
    private final MailService mailService;

    @Operation(summary = "인증 코드 확인 함수", description = "인증 코드 확인 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "인증 코드가 틀림",
                    content = @Content(schema = @Schema(implementation = CodeDifException.class))
            )
    })
    @PostMapping("/code")
    public ResponseEntity<String> authCodeCheck(@RequestBody @Valid AuthCodeRequest request) {
        String ip = IpSearchService.getClientIP();
        authLogCreationService.createAuthLog(request.getEmail(), ip);

        authMailSearchService.authCodeSameWithInputCode(request.getEmail(), request.getAuthCode());

        authMailModificationService.modifyIsSuccess(request.getEmail(), request.getAuthCode());

        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "인증 메일 전송 함수", description = "인증 메일 전송 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "인증 메일 10분 대기 시간",
                    content = @Content(schema = @Schema(implementation = AlreadySendCodeException.class))
            ),
            @ApiResponse(responseCode = "503", description = "메일 전송 횟수 최대치 도달",
                    content = @Content(schema = @Schema(implementation = MailServiceUnavailableException.class)))
    })
    @PostMapping("/mail")
    public ResponseEntity<String> authMailSend(@RequestBody @Valid AuthMailRequest request) {

        authMailSearchService.authCodeNotExist(request.getEmail());

        mailService.sendMail(request.getEmail());

        return ResponseEntity.ok("ok");
    }


    @Operation(summary = "토큰 재발송 함수", description = "토큰 재발송 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(schema = @Schema(implementation = RefreshTokenResponse.class))),
            @ApiResponse(responseCode = "404", description = "리프레시 토큰이 redis에 없습니다.",
                    content = @Content(schema = @Schema(implementation = TokenNotFoundException.class))
            )
    })
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody @Valid TokenRequest request) {
        String beforeRefreshToken = request.getRefreshToken();

        String id = tokenValidationService.searchTokenUserId(beforeRefreshToken);

        tokenValidationService.validationRefreshToken(beforeRefreshToken);

        tokenDeletionService.deleteRefreshToken(request.getAccessToken(), request.getRefreshToken());
        String accessToken = tokenCreationService.createAccessToken(id);
        String refreshToken = tokenCreationService.createRefreshToken(id);

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }
}
