package sosteam.throwapi.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sosteam.throwapi.domain.oauth.entity.Tokens;
import sosteam.throwapi.global.service.JwtTokenService;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokensGenerateService {
    @Value("${jwt.secret.grantType}")
    private static final String BEARER_TYPE = "Bearer";
    @Value("${jwt.secret.access-token-validity-in-seconds}") //43200 초
    private static long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${jwt.secret.refresh-token-validity-in-seconds}") //1209600 초
    private static long REFRESH_TOKEN_EXPIRE_TIME;
    @Value("${jwt.secret.access-token-kind}") //"accessToken"
    private static String ACCESS_TOKEN_KIND;
    @Value("${jwt.secret.refresh-token-kind}") //"refreshToken"
    private static String REFRESH_TOKEN_KIND;

    private final JwtTokenService jwtTokenService;

    public Tokens generate(UUID memberId, String inputId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String id = memberId.toString();
        String accessToken = jwtTokenService.generate(inputId, ACCESS_TOKEN_KIND, accessTokenExpiredAt);
        String refreshToken = jwtTokenService.generate(id, REFRESH_TOKEN_KIND, refreshTokenExpiredAt);

        return Tokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public Long extractMemberId(String accessToken) {
        return Long.valueOf(jwtTokenService.extractSubject(accessToken));
    }
}