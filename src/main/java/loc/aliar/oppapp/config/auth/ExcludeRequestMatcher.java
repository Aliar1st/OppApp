package loc.aliar.oppapp.config.auth;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RequestMatcher} that will return true if none of the passed in
 * {@link RequestMatcher} instances match.
 */
@CommonsLog
public class ExcludeRequestMatcher implements RequestMatcher {
    private final List<RequestMatcher> requestMatchers;

    /**
     * Creates a new instance
     *
     * @param requestMatchers the {@link RequestMatcher} instances to exclude
     */
    public ExcludeRequestMatcher(List<RequestMatcher> requestMatchers) {
        Assert.notEmpty(requestMatchers, "requestMatchers must contain a value");
        if (requestMatchers.contains(null)) {
            throw new IllegalArgumentException("requestMatchers cannot contain null values");
        }
        this.requestMatchers = requestMatchers;
    }

    /**
     * Creates a new instance
     *
     * @param requestMatchers the {@link RequestMatcher} instances to exclude
     */
    public ExcludeRequestMatcher(RequestMatcher... requestMatchers) {
        this(Arrays.asList(requestMatchers));
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        for (RequestMatcher matcher : requestMatchers) {
            log.debug("Trying to do not match using " + matcher);

            if (matcher.matches(request)) {
                log.debug("matched");
                return false;
            }
        }
        log.debug("No matches found");
        return true;
    }
}
