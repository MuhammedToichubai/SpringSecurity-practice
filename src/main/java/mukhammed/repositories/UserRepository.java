package mukhammed.repositories;

import mukhammed.dto.response.ResponseUserInnerPage;
import mukhammed.dto.response.UserProfile;
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

    @Query("select case when count(u) > 0 then true else false end from User u where u.role = 0")
    Boolean existByAdmin();

    @Query("select new mukhammed.dto.response.UserProfile(u.email, u.phoneNumber) from User u where u.id = :userID")
    Optional<UserProfile> getUserByID(Long userID);
}
