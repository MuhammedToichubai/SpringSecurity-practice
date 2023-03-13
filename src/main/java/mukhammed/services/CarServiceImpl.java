package mukhammed.services;

import jakarta.transaction.Transactional;
import mukhammed.dto.request.CarRequest;
import mukhammed.dto.response.ResponseCarInnerPage;
import mukhammed.dto.response.ResponseCarsPage;
import mukhammed.dto.response.SimpleResponse;
import mukhammed.models.Car;
import mukhammed.models.CarInfo;
import mukhammed.repositories.CarInfoRepository;
import mukhammed.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Service
@Transactional
public class CarServiceImpl {
    private final CarRepository carRepository;
    private final CarInfoRepository carInfoRepository;

    public CarServiceImpl(CarRepository carRepository, CarInfoRepository carInfoRepository) {
        this.carRepository = carRepository;
        this.carInfoRepository = carInfoRepository;
    }

    public SimpleResponse save(CarRequest request) {
        Car car = new Car();
        car.setBrand(request.brand());
        car.setModel(request.model());

        CarInfo carInfo = new CarInfo();
        carInfo.setYearOfIssue(request.yearOfIssue());
        carInfo.setEngine(request.engine());
        carInfo.setColor(request.color());
        carInfo.setPrice(request.price());

        car.setCarInfo(carInfo);
        carInfo.setCar(car);
        car.setCreatedAt(LocalDate.now());
        carRepository.save(car);
        carInfoRepository.save(carInfo);

        return SimpleResponse.builder().status("SAVE").massage("Successfully saved car.").build();
    }

    public List<ResponseCarsPage> findAll() {
        return carRepository.findAllCars();
    }

    public ResponseCarInnerPage findById(Long carId) {
        return carRepository.findCarById(carId).orElseThrow(() ->
                new IllegalArgumentException("Car with id: "+ carId+" not found"));
    }

    public SimpleResponse deleteById(Long carId) {
        if (!carRepository.existsById(carId)){
            throw  new RuntimeException("Car with id: "+ carId+ "not found!");
        }
        carInfoRepository.deleteCarInfoByCarId(carId);
        carRepository.deleteCarById(carId);

        return SimpleResponse.builder().status("DELETE").massage("Successfully deleted car.").build();
    }
}
