package mukhammed.api;

import mukhammed.dto.request.CarRequest;
import mukhammed.dto.response.ResponseCarInnerPage;
import mukhammed.dto.response.ResponseCarsPage;
import mukhammed.dto.response.SimpleResponse;
import mukhammed.services.CarServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequestMapping("/api/cars")
public class CarAPI {

    private final CarServiceImpl carService;

    public CarAPI(CarServiceImpl carService) {
        this.carService = carService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{userID}")
    public SimpleResponse save(@RequestBody CarRequest request,
                               @PathVariable Long userID) {
        return carService.save(request, userID);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'VENDOR')")
    @GetMapping
    public List<ResponseCarsPage> findAll() {
        return carService.findAll();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{carId}")
    public ResponseCarInnerPage findById(@PathVariable Long carId) {
        return carService.findById(carId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'VENDOR')")
    @DeleteMapping("/{carId}")
    public SimpleResponse deleteById(@PathVariable Long carId) {
        return carService.deleteById(carId);
    }


    @GetMapping("/search")
    public List<ResponseCarsPage> searchByBrandAndModel(
            @RequestParam (required = false) String brand,
            @RequestParam (required = false) String model
    ){
       return carService.searchByBrandAndModel(brand, model);
    }

}
