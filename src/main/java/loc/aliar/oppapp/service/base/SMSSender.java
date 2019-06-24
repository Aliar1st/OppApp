package loc.aliar.oppapp.service.base;

import loc.aliar.oppapp.service.MessageSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SMSSender implements MessageSender {
    @Override
    public boolean send(List<String> recipients, String subject, String message) {
        return send(recipients, subject + "\n\n" + message);
    }

    @Override
    public boolean send(List<String> recipients, String message) {
        System.out.println(message);
        return true;
    }
}
