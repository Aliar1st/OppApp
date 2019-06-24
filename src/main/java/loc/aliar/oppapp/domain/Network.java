package loc.aliar.oppapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.Max;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Network extends AuditResourceEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Max(255)
    private String link;

    @ManyToOne
    private NetworkType networkType;

    @CreatedBy
    @ManyToOne
    private User createdBy;
}
