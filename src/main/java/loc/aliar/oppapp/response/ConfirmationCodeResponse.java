package loc.aliar.oppapp.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ConfirmationCodeResponse extends ResourceSupport {
    private final Boolean isCodeCorrect;
    private Boolean isUserRegistered;
    private Long userId;
    @JsonIgnore
    private String jwtToken;
}
