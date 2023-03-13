package mukhammed.repositories;

import jakarta.transaction.Transactional;
import mukhammed.dto.response.ResponseCarInnerPage;
import mukhammed.dto.response.ResponseCarsPage;
import mukhammed.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select new mukhammed.dto.response.ResponseCarsPage(c.brand, c.model, c.carInfo.yearOfIssue) from Car c ")
    List<ResponseCarsPage> findAllCars();

    @Query("select new mukhammed.dto.response" +
            ".ResponseCarInnerPage(ci.yearOfIssue, ci.engine, ci.color, ci.price, c.createdAt, u.phoneNumber) " +
            "from Car c join CarInfo ci on c.carInfo.id = ci.id " +
            "join User u  on  u.id = c.owner.id where c.id = ?1")
    Optional<ResponseCarInnerPage> findCarById(Long carId);


    @Modifying
    @Query(value = "delete from Car c where c.id = :carId")
    void deleteCarById(Long carId);
}
