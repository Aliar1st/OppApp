package loc.aliar.oppapp.controller;

import loc.aliar.oppapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/{id}")
public class UserController {
    private final UserService userService;

//    @GetMapping("/sendCode")
//    public ResponseEntity<?> sendCode(@PathVariable Long id, @RequestParam String newPhone) {
//        authService.createAndSendCode(phone);
//
//        EmptyResponse linkResponse = new EmptyResponse();
//        linkResponse.add(getPhoneStatusLink(phone));
//        linkResponse.add(getConfirmCodeLink(phone));
//
//        return ResponseEntity.ok(linkResponse);
//    }
//
//    @GetMapping("/confirmCode")
//    public ResponseEntity<?> confirmCode(@RequestParam String phone, @RequestParam String code) {
//        ConfirmationCodeResponse codeResponse = authService.confirmCodeAndCreateToken(phone, code);
//        codeResponse.add(getPhoneStatusLink(phone));
//
//        return ResponseEntity.ok()
//                .header(JwtProvider.TOKEN_HEADER, JwtProvider.TOKEN_TYPE + codeResponse.getJwtToken())
//                .body(codeResponse);
//    }
}