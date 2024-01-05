package beratyesbek.youtube.mock.model.dto.user;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    private String firstname;
    private String lastname;
    private String phone;
}
