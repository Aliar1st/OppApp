package loc.aliar.oppapp.config.auth;

import loc.aliar.oppapp.domain.User;
import loc.aliar.oppapp.exception.InvalidJwtException;
import loc.aliar.oppapp.exception.UserNotFoundException;
import loc.aliar.oppapp.service.UserService;
import loc.aliar.oppapp.util.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(JwtAuthenticationToken.class, authentication,
                () -> "Only JwtAuthenticationToken is supported");

        JwtAuthenticationToken jwtAuth = ((JwtAuthenticationToken) authentication);
        String token = jwtAuth.getCredentials();

        if (!StringUtils.hasText(token)) {
            throw new InvalidJwtException("Empty token");
        }

        jwtProvider.validateToken(token);

        Long userId = jwtProvider.getUserIdFromToken(token);

        User user = userService.get(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + "not found"));


        JwtAuthenticationToken jwtAuthenticated = new JwtAuthenticationToken(user, token, user.getAuthorities());
        jwtAuthenticated.setDetails(jwtAuth.getDetails());

        return jwtAuthenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
