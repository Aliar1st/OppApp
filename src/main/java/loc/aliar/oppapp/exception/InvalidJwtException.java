package loc.aliar.oppapp.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtException extends AuthenticationException {
    public InvalidJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidJwtException(String msg) {
        super(msg);
    }
}
