package loc.aliar.oppapp.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditResourceEntity {
    @Column(columnDefinition = "timestamp", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    //@Column(columnDefinition = "bigint(20)", updatable = false)
    @ManyToOne
    @CreatedBy
    private User createdBy;

    @Column(columnDefinition = "timestamp")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    //@Column(columnDefinition = "bigint(20)")
    @ManyToOne
    @LastModifiedBy
    private User updatedBy;
}
