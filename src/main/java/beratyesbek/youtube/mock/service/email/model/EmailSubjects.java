package beratyesbek.youtube.mock.service.email.model;

import lombok.Getter;

@Getter
public enum EmailSubjects {
    COMPLETE_YOUR_REGISTRATION("Complete Your Registration");

    private final String subject;

    EmailSubjects(String subject) {
        this.subject = subject;
    }
}
