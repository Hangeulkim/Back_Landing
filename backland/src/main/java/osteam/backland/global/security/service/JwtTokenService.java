package osteam.backland.global.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import osteam.backland.global.attribute.Token;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenService {
    private final Key KEY;
    private final AuthenticationService userDetailsService;

    public JwtTokenService(@Value("${jwt.secret.key}") String secretKey, AuthenticationService userDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.KEY = Keys.hmacShaKeyFor(keyBytes);
        this.userDetailsService = userDetailsService;
    }

    public String createToken(String id, Token token) {
        Date now = new Date();
        String jwtToken = Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(now.getTime() + token.getMilliseconds()))
                .claim("kind", token.getType())
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();

        return jwtToken;
    }

    public String getData(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Date getExpireDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getData(token));
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }

    public String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token, Token type) {
        try {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getExpiration();
            return expiration.after(new Date()) && isToken(token, type);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isToken(String token, Token type) {
        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return body.get("kind") == type.getType();
        } catch (Exception e) {
            return false;
        }

    }
}
