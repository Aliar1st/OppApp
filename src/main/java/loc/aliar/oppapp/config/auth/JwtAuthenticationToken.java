package loc.aliar.oppapp.config.auth;

import loc.aliar.oppapp.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String token;
    private final User user;

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>JwtAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     */
    public JwtAuthenticationToken(String token) {
        super(null);
        this.user = null;
        this.token = token;
        super.setAuthenticated(false);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public JwtAuthenticationToken(User user, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
        this.token = token;
        super.setAuthenticated(true);
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    /**
     * The JWT that populate the principal in <tt>JwtAuthenticationProvider</tt>.
     * Callers are expected to populate the credentials.
     *
     * @return the token with the help of witch is populated the <code>Principal</code>
     */
    @Override
    public String getCredentials() {
        return token;
    }

    /**
     * The principal being authenticated. This is the <tt>User</tt> object.
     * <tt>JwtAuthenticationProvider</tt> will create a {@code User} object as the principal.
     *
     * @return the <code>Principal</code> being the authenticated {@code User} after authentication.
     */
    @Override
    public User getPrincipal() {
        return user;
    }
}
