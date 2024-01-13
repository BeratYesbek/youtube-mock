package beratyesbek.youtube.mock.service.email.model;

public enum EmailTemplates {
    EMAIL_VERIFY("email-verify");

    private final String templateName;

    EmailTemplates(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
}
