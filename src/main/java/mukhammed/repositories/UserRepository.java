package mukhammed.repositories;

import mukhammed.dto.response.ResponseUserInnerPage;
import mukhammed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select new mukhammed.dto.response.ResponseUserInnerPage(u.email, u.phoneNumber, u.role)" +
            "from User u where u.id = ?1")
    Optional<ResponseUserInnerPage> findUserByID(Long userId);
}
