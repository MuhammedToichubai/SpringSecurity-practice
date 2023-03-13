package mukhammed.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author Mukhammed Asantegin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarsPage {
    private String brand;
    private String model;
    private LocalDate yearOfIssue;
}
