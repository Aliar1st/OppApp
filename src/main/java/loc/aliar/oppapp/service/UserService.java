package loc.aliar.oppapp.service;

import loc.aliar.oppapp.domain.Role;
import loc.aliar.oppapp.domain.User;

import java.util.Optional;

public interface UserService {
    Optional<User> get(Long id);

    Optional<User> getByPhone(String phone);

    User register(String phone, Role.Roles role);
}
