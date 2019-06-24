package loc.aliar.oppapp.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import loc.aliar.oppapp.exception.InvalidJwtException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;

@CommonsLog
@Component
public class JwtProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "Bearer ";

    @Value("${app.jwt.secret:J@NcRfUjXn2r5u8x/A?D*G-KaPdSgVkYp3s6v9y$B&E)H+MbQeThWmZq4t7w!z%C}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:604800000}")
    private Integer jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Long userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public void validateToken(String token) {
        Exception exception;
        String message;

        try {
            Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token);
            return;
        } catch (SignatureException e) {
            exception = e;
            message = "Invalid JWT signature";
        } catch (MalformedJwtException e) {
            exception = e;
            message = "Invalid JWT token";
        } catch (ExpiredJwtException e) {
            exception = e;
            message = "Expired JWT token";
        } catch (UnsupportedJwtException e) {
            exception = e;
            message = "Unsupported JWT token";
        } catch (IllegalArgumentException e) {
            exception = e;
            message = "JWT claims string is empty";
        }

        throw new InvalidJwtException(message, exception);
    }
}
