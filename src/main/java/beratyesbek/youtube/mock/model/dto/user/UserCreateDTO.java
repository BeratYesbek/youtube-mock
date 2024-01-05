package beratyesbek.youtube.mock.model.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
        @NotBlank(message = "firstName cannot be blank")
        String firstname;

        @NotBlank(message = "lastName cannot be blank")
        String lastname;

        @NotBlank(message = "email cannot be blank")
        @Email(message = "firstName must be a valid email")
        String email;

        @NotBlank(message = "phone cannot be blank")
        String phone;

}
