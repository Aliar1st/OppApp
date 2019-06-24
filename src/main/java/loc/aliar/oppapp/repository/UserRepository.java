package loc.aliar.oppapp.repository;

import loc.aliar.oppapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    @RestResource(exported = false)
    Optional<User> findByPhone(String phone);

    @Override
    @PreAuthorize("hasPermission(#entity, 'save')")
    <S extends User> S save(@Param("entity") S entity);

    @Override
    @RestResource(exported = false)
    void delete(User entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
}
