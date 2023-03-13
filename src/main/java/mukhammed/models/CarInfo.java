package mukhammed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Mukhammed Asantegin
 */
@Data
@Entity
@Table(name = "car_info")
@NoArgsConstructor
@AllArgsConstructor
public class CarInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate yearOfIssue;
    private String engine;
    private String color;
    private BigDecimal price;
    @OneToOne(cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private Car car;
}
