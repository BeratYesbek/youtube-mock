package beratyesbek.youtube.mock.service.email.model;


import lombok.*;
import org.thymeleaf.context.Context;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String to;
    private EmailSubjects subject;
    private EmailTemplates template;
    private Context context;
}
