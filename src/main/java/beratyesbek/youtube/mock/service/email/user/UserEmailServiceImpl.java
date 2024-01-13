package beratyesbek.youtube.mock.service.email.user;


import beratyesbek.youtube.mock.service.email.EmailService;
import beratyesbek.youtube.mock.service.email.model.EmailMessage;
import beratyesbek.youtube.mock.service.email.model.EmailRequest;
import beratyesbek.youtube.mock.service.email.model.EmailSubjects;
import beratyesbek.youtube.mock.service.email.model.EmailTemplates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserEmailServiceImpl implements UserEmailService {
    private final EmailService emailService;

    @Override
    public void sendVerificationEmail(String email, String firstName, String lastName) {

        Context context = new Context();
        context.setVariable("firstName", firstName);
        context.setVariable("lastName", lastName);
        context.setVariable("url", "http://localhost:8080/api/auth/verify?email=" + email + "&token=" + UUID.randomUUID());
        context.setVariable("message", EmailMessage.VERIFY_ACCOUNT.getMessage());

        emailService.send(
                EmailRequest.builder()
                        .to(email)
                        .subject(EmailSubjects.COMPLETE_YOUR_REGISTRATION)
                        .template(EmailTemplates.EMAIL_VERIFY)
                        .context(context)
                        .build()
        );
    }



}
