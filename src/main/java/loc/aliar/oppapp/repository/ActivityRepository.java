package loc.aliar.oppapp.repository;

import loc.aliar.oppapp.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Override
    @PreAuthorize("hasPermission(entity, 'save')")
    <S extends Activity> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(Activity entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
}
