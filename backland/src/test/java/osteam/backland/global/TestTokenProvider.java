package osteam.backland.global;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import osteam.backland.global.attribute.Token;
import osteam.backland.global.security.service.AuthenticationService;
import osteam.backland.global.security.service.JwtTokenService;

@Primary
@Component
public class TestTokenProvider extends JwtTokenService {

    private final AuthenticationService authenticationService;

    public TestTokenProvider(AuthenticationService authenticationService) {
        super("testtesttesttesttesttesttesttesttesttesttesttesttesttest", authenticationService);
        this.authenticationService = authenticationService;
    }

    @Override
    public String createToken(String id, Token token) {
        return "this-is-test-token";
    }

    @Override
    public String getData(String token) {
        return "testID";
    }

    @Override
    public boolean validateToken(String token, Token type) {
        return token.equals("this-is-test-token") || token.equals("this-is-access-token") || token.equals("this-is-refresh-token");
    }

    @Override
    public boolean isToken(String token, Token type) {
        return token.equals("this-is-test-token");
    }
}
