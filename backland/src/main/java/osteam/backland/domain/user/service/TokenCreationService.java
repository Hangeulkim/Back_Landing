package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.global.attribute.Token;
import osteam.backland.global.security.service.JwtTokenService;
import osteam.backland.global.security.service.RedisService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenCreationService {

    private final JwtTokenService jwtTokenService;

    private final RedisService redisService;

    public String createAccessToken(String id) {

        return jwtTokenService.createToken(id, Token.ACCESS);
    }

    public String createRefreshToken(String id) {
        String refreshToken = jwtTokenService.createToken(id, Token.REFRESH);

        redisService.setDataExpire(id, refreshToken, Token.REFRESH.getMilliseconds());

        return refreshToken;
    }
}
