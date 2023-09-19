package osteam.backland.domain.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.account.exception.TokenNotFoundException;
import osteam.backland.global.attribute.Token;
import osteam.backland.global.security.service.JwtTokenService;
import osteam.backland.global.security.service.RedisService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenValidationService {

    private final JwtTokenService jwtTokenService;

    private final RedisService redisService;

    public String searchTokenUserId(String token) {
        return jwtTokenService.getData(token);
    }

    public boolean validationRefreshToken(String refreshToken) {
        String data = redisService.getData(refreshToken);

        if (data == null) {
            throw new TokenNotFoundException();
        }
        jwtTokenService.validateToken(refreshToken, Token.REFRESH);

        return true;
    }
}
