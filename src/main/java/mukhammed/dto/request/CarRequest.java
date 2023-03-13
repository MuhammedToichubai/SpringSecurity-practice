package mukhammed.dto.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record CarRequest (
        String brand,
        String model,
        LocalDate yearOfIssue,
        String engine,
        String color,
        BigDecimal price
){
}
