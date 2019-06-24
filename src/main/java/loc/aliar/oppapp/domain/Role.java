package loc.aliar.oppapp.domain;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role implements GrantedAuthority {
    private static final long ROLE_USER_ID = 1;
    private static final long ROLE_ADMIN_ID = 2;

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String authority;

    @Getter
    public enum Roles {
        USER(ROLE_USER_ID),
        ADMIN(ROLE_ADMIN_ID);

        private long id;

        Roles(long id) {
            this.id = id;
        }
    }
}
