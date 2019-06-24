package loc.aliar.oppapp.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@RequiredArgsConstructor
public class PhoneStatusResponse extends ResourceSupport {
    private final boolean registered;
}
