package loc.aliar.oppapp.repository;

import loc.aliar.oppapp.domain.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, String> {
    Optional<ConfirmationCode> findByPhone(String phone);
}
