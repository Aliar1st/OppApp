package loc.aliar.oppapp.service;

import loc.aliar.oppapp.response.ConfirmationCodeResponse;
import loc.aliar.oppapp.response.PhoneStatusResponse;

public interface AuthService {
    void createAndSendCode(String phone);

    PhoneStatusResponse getPhoneStatus(String phone);

    ConfirmationCodeResponse confirmCodeAndCreateToken(String phone, String code);
}
