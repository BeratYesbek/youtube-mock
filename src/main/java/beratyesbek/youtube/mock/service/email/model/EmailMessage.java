package beratyesbek.youtube.mock.service.email.model;


public enum EmailMessage {

    VERIFY_ACCOUNT("Your account has been created. Please click on the link below to verify your account.");
    private final String message;

    EmailMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
