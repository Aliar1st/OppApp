package loc.aliar.oppapp.domain;

import java.time.LocalDateTime;

public class UserComment {
    private User recipient;
    private User sender;
    private String text;
    private LocalDateTime time;
}
