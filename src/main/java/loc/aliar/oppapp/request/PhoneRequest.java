package loc.aliar.oppapp.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PhoneRequest {
    @Pattern(regexp = "^(\\+)?\\d{10,14}$")
    private String phone;
}
