package mukhammed.services;

import jakarta.transaction.Transactional;
import mukhammed.dto.request.CarRequest;
import mukhammed.dto.response.ResponseCarInnerPage;
import mukhammed.dto.response.ResponseCarsPage;
import mukhammed.dto.response.SimpleResponse;
import mukhammed.models.Car;
import mukhammed.models.CarInfo;
import mukhammed.models.User;
import mukhammed.repositories.CarInfoRepository;
import mukhammed.repositories.CarRepository;
import mukhammed.repositories.UserRepository;
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
    private final UserRepository userRepository;

    public CarServiceImpl(CarRepository carRepository, CarInfoRepository carInfoRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.carInfoRepository = carInfoRepository;
        this.userRepository = userRepository;
    }

    public SimpleResponse save(CarRequest request, Long userID) {
        User user = userRepository.findById(userID).orElseThrow();
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
        user.addCar(car);
        car.setOwner(user);

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

    public List<ResponseCarsPage> searchByBrandAndModel(String brand, String model) {

        if (brand == null && model == null) return carRepository.findAllCars();
        else if(brand != null && model == null) return carRepository.findCarsByBrand(brand);
        else if (brand == null) return carRepository.findCarsByModel(model);
        else return carRepository.findCarsByBrandAndModel(brand, model);
    }
}
