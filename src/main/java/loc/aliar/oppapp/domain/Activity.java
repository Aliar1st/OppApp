package loc.aliar.oppapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Activity extends AuditResourceEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Min(4)
    @Max(100)
    @Column(length = 100)
    private String name;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Max(255)
    @Column
    private String description;

//    @ManyToOne(optional = false)
//    private City city;

    @ManyToOne(optional = false)
    private ActivityType activityType;
//    private Set<ActivityTag> activityTags;
//    private Set<User> moderators;
}
