package beratyesbek.youtube.mock.service.email;

import beratyesbek.youtube.mock.service.email.model.EmailRequest;
import jakarta.validation.constraints.NotNull;

public interface EmailService {
    void send(@NotNull EmailRequest emailRequest);
}
