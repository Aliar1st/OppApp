package loc.aliar.oppapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Min(2)
    @Max(255)
    private String name;
}
