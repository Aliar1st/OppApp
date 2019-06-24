package loc.aliar.oppapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
public class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void m() throws Exception {
        mockMvc.perform(
            options("/api/users")
        )
                .andDo(print());
    }
}