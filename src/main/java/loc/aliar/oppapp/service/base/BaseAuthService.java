package loc.aliar.oppapp.service.base;

import loc.aliar.oppapp.domain.ConfirmationCode;
import loc.aliar.oppapp.domain.Role;
import loc.aliar.oppapp.domain.User;
import loc.aliar.oppapp.repository.ConfirmationCodeRepository;
import loc.aliar.oppapp.response.ConfirmationCodeResponse;
import loc.aliar.oppapp.response.PhoneStatusResponse;
import loc.aliar.oppapp.service.AuthService;
import loc.aliar.oppapp.service.MessageSender;
import loc.aliar.oppapp.service.UserService;
import loc.aliar.oppapp.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

@CommonsLog
@RequiredArgsConstructor
@Service
@Transactional
public class BaseAuthService implements AuthService {
    private final MessageSender messageSender;
    private final UserService userService;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void createAndSendCode(String phone) {
        String code = String.valueOf(ThreadLocalRandom.current().nextInt(100_000, 1_000_000));

        ConfirmationCode confirmationCode = confirmationCodeRepository.findByPhone(phone)
                .map(confCode -> {
                    confCode.setCode(code);
                    return confCode;
                })
                .orElse(new ConfirmationCode(phone, code));

        confirmationCodeRepository.save(confirmationCode);

        messageSender.send(Collections.singletonList(phone), code);
    }

    @Override
    public PhoneStatusResponse getPhoneStatus(String phone) {
        return new PhoneStatusResponse(userService.getByPhone(phone).isPresent());
    }

    @Override
    public ConfirmationCodeResponse confirmCodeAndCreateToken(String phone, String code) {
        String realCode = confirmationCodeRepository.findByPhone(phone)
                .map(ConfirmationCode::getCode)
                .orElse(null);

        if (Objects.equals(realCode, code)) {
            AtomicBoolean registered = new AtomicBoolean();

            User user = findOrRegister(phone, () -> registered.set(true));
            String token = jwtProvider.generateToken(user.getId());

            return new ConfirmationCodeResponse(true, registered.get(), user.getId(), token);
        } else {
            return new ConfirmationCodeResponse(false);
        }
    }

    private User findOrRegister(String phone, Runnable ifRegistered) {
        return userService.getByPhone(phone)
                .orElseGet(() -> {
                    User user = userService.register(phone, Role.Roles.USER);

                    ifRegistered.run();

                    return user;
                });
    }
}
