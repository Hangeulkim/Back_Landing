package osteam.backland.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osteam.backland.domain.auth.service.AuthMailSearchService;
import osteam.backland.domain.user.controller.request.LoginRequest;
import osteam.backland.domain.user.controller.request.TokenRequest;
import osteam.backland.domain.user.controller.request.UserSignUpRequest;
import osteam.backland.domain.user.controller.response.TokenResponse;
import osteam.backland.domain.user.exception.PasswordDifFromConfirmException;
import osteam.backland.domain.user.exception.UserNotFoundException;
import osteam.backland.domain.user.service.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserCreationService userCreationService;

    private final AuthMailSearchService authMailSearchService;


    private final UserDeletionService userDeletionService;

    private final TokenCreationService tokenCreationService;

    private final TokenDeletionService tokenDeletionService;

    private final UserValidationService userValidationService;

    @Operation(summary = "회원 가입 함수", description = "회원 가입 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "이미 중복되는 데이터가 존재합니다.")
    })
    @PostMapping("/signup")
    public ResponseEntity<String> authCode(@RequestBody @Valid UserSignUpRequest request) {
        //name과 email이 가입 된 정보가 있는지 확인
        userValidationService.userNameOrEmailExist(request.getUserName(), request.getEmail());

        //인증 성공으로 부터 10분 이내에 가입을 했는지
        authMailSearchService.authCodeSearchSuccess(request.getEmail());

        //비밀번호가 일치 하는지 확인 후 유저 정보를 저장
        userCreationService.createUser(request.getId(), request.getPwd(), request.getPwdConfirm(), request.getUserName(), request.getEmail());

        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "회원 탈퇴 함수", description = "회원 탈퇴 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "가입된 회원이 없습니다.",
                    content = @Content(schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody @Valid TokenRequest request) {
        //유저 정보 삭제
        userDeletionService.deleteUser(request.getAccessToken());

        //refreshtoken을 redis에서 삭제하고 accessToken을 이용하여 재 로그인 방지를 위한 등록 과정을 진행
        tokenDeletionService.deleteRefreshToken(request.getAccessToken(), request.getRefreshToken());

        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "로그인 함수", description = "로그인 함수 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "로그인에 실패 했습니다.",
                    content = @Content(schema = @Schema(implementation = PasswordDifFromConfirmException.class))),
            @ApiResponse(responseCode = "404", description = "가입된 회원이 없습니다.",
                    content = @Content(schema = @Schema(implementation = UserNotFoundException.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
        //유저 비밀번호가 일치하는지 확인
        userValidationService.userValidate(request.getId(), request.getPwd());

        String accessToken = tokenCreationService.createAccessToken(request.getId());
        String refreshToken = tokenCreationService.createRefreshToken(request.getId());

        return ResponseEntity.ok(new TokenResponse(accessToken, refreshToken));
    }

    @Operation(summary = "로그아웃 함수", description = "로그아웃 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "403", description = "로그아웃에 실패 했습니다.")
    })
    @DeleteMapping("/logout")
    public ResponseEntity<String> login(@RequestBody @Valid TokenRequest request) {
        //refreshtoken을 redis에서 삭제하고 accessToken을 이용하여 재 로그인 방지를 위한 등록 과정을 진행
        tokenDeletionService.deleteRefreshToken(request.getAccessToken(), request.getRefreshToken());

        return ResponseEntity.ok("ok");
    }

}
