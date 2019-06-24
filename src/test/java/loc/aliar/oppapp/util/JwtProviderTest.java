package loc.aliar.oppapp.util;

import loc.aliar.oppapp.exception.InvalidJwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtProviderTest {
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void whenGenerateTokenAndGetId_thenIdShouldBeEquals() {
        Long id = 1L;

        String token = jwtProvider.generateToken(id);

        assertEquals(id, jwtProvider.getUserIdFromToken(token));
    }

//    @Test
//    public void whenExpired_thenThrowsInvalidJwtException() {
//        ReflectionTestUtils.setField(jwtProvider, "jwtExpiration", -1);
//
//        String token = jwtProvider.generateToken(1L);
//
//        assertThrows(InvalidJwtException.class, () -> jwtProvider.validateToken(token));
//    }

    @Test
    public void whenChangeToken_thenThrowsInvalidJwtException() {
        String token = jwtProvider.generateToken(1L) + "1";

        assertThrows(InvalidJwtException.class, () -> jwtProvider.validateToken(token));
    }
}