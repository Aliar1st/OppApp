package loc.aliar.oppapp.repository;

import loc.aliar.oppapp.domain.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    @Override
    @PreAuthorize("hasPermission(entity, 'save')")
    <S extends ActivityType> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(ActivityType entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
}
