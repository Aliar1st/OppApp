package loc.aliar.oppapp.controller;

import loc.aliar.oppapp.response.ConfirmationCodeResponse;
import loc.aliar.oppapp.response.PhoneStatusResponse;
import loc.aliar.oppapp.service.AuthService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthController.class, secure = false)
public class AuthControllerTest {

    private static final String BASE_PATH = "/api/auth/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    @BeforeAll
    public void setUp() {
    }

    @Test
    public void contextLoad() {
        assertNotNull(authController);
    }

    @Test
    public void whenRequestCode_thenSendCode() throws Exception {
        mockMvc.perform(
                get(BASE_PATH + "sendCode").contentType(APPLICATION_JSON)
                        .param("phone", "+799911112233")
        )
                .andExpect(status().is(OK.value()))
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(content().json(
                        "{_links:" +
                            "{phoneStatus:" +
                                "{href:" +
                                    "\"http://localhost/api/auth/phoneStatus?phone=+799911112233\"" +
                                "}" +
                            "," +
                            "confirmCode:" +
                                "{href:" +
                                    "\"http://localhost/api/auth/confirmCode?phone=+799911112233&code=\"" +
                                "}" +
                            "}" +
                        "}"
                ));

        verify(authService, times(1)).createAndSendCode("+799911112233");
    }

    @Test
    public void whenConfirmCode_thenCreateAndSendToken() throws Exception {
        when(authService.confirmCodeAndCreateToken("+799911112233", "111111"))
                .thenReturn(new ConfirmationCodeResponse(true, false, 0L, "token123"));

        mockMvc.perform(
                get(BASE_PATH + "confirmCode").contentType(APPLICATION_JSON)
                        .param("phone", "+799911112233")
                        .param("code", "111111")
        )
                .andExpect(status().is(OK.value()))
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(header().string("Authorization", "Bearer token123"))
                .andExpect(content().json(
                        "{isCodeCorrect:true," +
                        "isUserRegistered:false," +
                        "userId:0," +
                        "_links:" +
                            "{phoneStatus:" +
                                "{href:" +
                                    "\"http://localhost/api/auth/phoneStatus?phone=+799911112233\"" +
                                "}" +
                            "}" +
                        "}"
                ));

        verify(authService, times(1)).confirmCodeAndCreateToken("+799911112233", "111111");
    }

    @Test
    public void whenRequestPhoneStatus_thenSendIsPhoneRegistered() throws Exception {
        when(authService.getPhoneStatus("+799911112233")).thenReturn(new PhoneStatusResponse(true));

        mockMvc.perform(
                get(BASE_PATH + "phoneStatus").contentType(APPLICATION_JSON)
                        .param("phone", "+799911112233")
        )
                .andExpect(status().is(OK.value()))
                .andExpect(content().contentType("application/hal+json;charset=UTF-8"))
                .andExpect(content().json(
                        "{registered:true}"
                ));

        verify(authService, times(1)).getPhoneStatus("+799911112233");
    }
}