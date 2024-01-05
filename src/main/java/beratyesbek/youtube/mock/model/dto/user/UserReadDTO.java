package beratyesbek.youtube.mock.model.dto.user;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReadDTO{
    String id;
    String firstname;
    String lastname;
    String email;
    String phone;
    String password;
}
