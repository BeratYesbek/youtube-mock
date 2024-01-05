
package beratyesbek.youtube.mock.repository;

import beratyesbek.youtube.mock.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, String> {

        Boolean existsByEmail(String email);

        Boolean existsByPhone(String phone);

        @NotNull
        @Query("SELECT u FROM User u WHERE u.deleted = false AND ((:firstname IS NULL OR LOWER(u.firstname) " +
                "LIKE LOWER(CONCAT('%', :firstname, '%'))) AND (:lastname IS NULL OR LOWER(u.lastname) LIKE LOWER(CONCAT('%', :lastname, '%'))))")
        Page<User> findAll(@NotNull Pageable pageable, @Param("firstname") String firstname, @Param("lastname") String lastname);
}
