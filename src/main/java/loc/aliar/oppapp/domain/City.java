package loc.aliar.oppapp.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Min(2)
    @Max(255)
    private String name;

    @ManyToOne
    private Country country;
}
