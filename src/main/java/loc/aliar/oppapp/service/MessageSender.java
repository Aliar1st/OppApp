package loc.aliar.oppapp.service;

import java.util.List;

public interface MessageSender {
    boolean send(List<String> recipients, String subject, String message);
    boolean send(List<String> recipients, String message);
}
