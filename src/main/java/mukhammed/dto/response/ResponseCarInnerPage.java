package mukhammed.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mukhammed Asantegin
 */
@Builder
public record ResponseCarInnerPage(
        LocalDate yearOfIssue,
        String engine,
        String color,
        BigDecimal price,
        LocalDate createdAt,
        String ownerPhoneNumber) {
}
