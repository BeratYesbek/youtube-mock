package beratyesbek.youtube.mock.model;


import beratyesbek.youtube.mock.model.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE USERS SET DELETED = TRUE WHERE ID=? and VERSION=?")
public class User extends BaseEntity {

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PHONE", nullable = false, unique = true)
    private String phone;

    @Column(name = "PASSWORD", nullable = true)
    private String password;


}
