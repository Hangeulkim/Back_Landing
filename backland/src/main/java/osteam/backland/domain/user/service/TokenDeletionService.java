package osteam.backland.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.global.security.service.JwtTokenService;
import osteam.backland.global.security.service.RedisService;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenDeletionService {

    private final RedisService redisService;

    private final JwtTokenService jwtTokenService;

    public void deleteRefreshToken(String accessToken, String refreshToken) {
        String id = jwtTokenService.getData(refreshToken);
        Date expireDate = jwtTokenService.getExpireDate(accessToken);
        Date now = new Date();

        if (expireDate.after(now)) {
            redisService.setDataExpire(accessToken, id, expireDate.getTime() - now.getTime());
        }

        redisService.deleteData(refreshToken);
    }
}
