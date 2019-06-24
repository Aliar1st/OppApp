package loc.aliar.oppapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class NetworkType extends AuditResourceEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    @Min(2)
    @Max(50)
    private String name;
}
