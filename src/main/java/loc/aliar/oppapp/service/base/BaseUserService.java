package loc.aliar.oppapp.service.base;

import loc.aliar.oppapp.domain.User;
import loc.aliar.oppapp.repository.RoleRepository;
import loc.aliar.oppapp.repository.UserRepository;
import loc.aliar.oppapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static loc.aliar.oppapp.domain.Role.Roles;

@CommonsLog
@RequiredArgsConstructor
@Service
@Transactional
public class BaseUserService implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User register(String phone, Roles role) {
        return userRepository.save(new User(phone, roleRepository.getOne(role.getId())));
    }
}
