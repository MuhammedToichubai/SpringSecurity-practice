package mukhammed.repositories;

import mukhammed.models.CarInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Mukhammed Asantegin
 */
@Repository
public interface CarInfoRepository extends JpaRepository<CarInfo , Long> {
    @Modifying
    @Query(value = "delete from CarInfo ci  where ci.car.id = :carId")
    void deleteCarInfoByCarId(Long carId);
}
