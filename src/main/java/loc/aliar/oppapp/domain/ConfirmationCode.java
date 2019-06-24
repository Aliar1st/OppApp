package loc.aliar.oppapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationCode {
    @Id
    @GeneratedValue
    private Long id;

    @Pattern(regexp = "^(\\+)?\\d{10,14}$")
    @NaturalId
    @Column(length = 15)
    private String phone;

    @Length(min = 6, max = 6)
    @Column(nullable = false, columnDefinition = "char(6)")
    private String code;

    public ConfirmationCode(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
}
