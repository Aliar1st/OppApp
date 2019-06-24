package loc.aliar.oppapp.controller;

import loc.aliar.oppapp.response.ConfirmationCodeResponse;
import loc.aliar.oppapp.response.EmptyResponse;
import loc.aliar.oppapp.service.AuthService;
import loc.aliar.oppapp.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/sendCode")
    public ResponseEntity<?> sendCode(String phone) {
        authService.createAndSendCode(phone);

        EmptyResponse linkResponse = new EmptyResponse();
        linkResponse.add(getPhoneStatusLink(phone));
        linkResponse.add(getConfirmCodeLink(phone));

        return ResponseEntity.ok(linkResponse);
    }

    @GetMapping("/confirmCode")
    public ResponseEntity<?> confirmCode(@RequestParam String phone, @RequestParam String code) {
        ConfirmationCodeResponse codeResponse = authService.confirmCodeAndCreateToken(phone, code);
        codeResponse.add(getPhoneStatusLink(phone));

        return ResponseEntity.ok()
                .header(JwtProvider.TOKEN_HEADER, JwtProvider.TOKEN_TYPE + codeResponse.getJwtToken())
                .body(codeResponse);
    }

    @GetMapping("/phoneStatus")
    public ResponseEntity<?> phoneStatus(@RequestParam String phone) {
        return ResponseEntity.ok(authService.getPhoneStatus(phone));
    }

    private Link getPhoneStatusLink(String phone) {
        return linkTo(methodOn(AuthController.class).phoneStatus(phone)).withRel("phoneStatus");
    }

    private Link getConfirmCodeLink(String phone) {
        return linkTo(methodOn(AuthController.class).confirmCode(phone, "")).withRel("confirmCode");
    }
}