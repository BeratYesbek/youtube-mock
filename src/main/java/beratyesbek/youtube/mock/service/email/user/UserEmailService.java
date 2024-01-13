package beratyesbek.youtube.mock.service.email.user;


public interface UserEmailService {

    void sendVerificationEmail(String email, String firstName, String lastName);

}
