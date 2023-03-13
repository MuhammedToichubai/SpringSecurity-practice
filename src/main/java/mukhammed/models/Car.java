package mukhammed.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Mukhammed Asantegin
 */
@Data
@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
    private CarInfo carInfo;
    private LocalDate createdAt;
    @ManyToOne(cascade = {REFRESH, PERSIST, DETACH, MERGE})
    private User owner;
}
