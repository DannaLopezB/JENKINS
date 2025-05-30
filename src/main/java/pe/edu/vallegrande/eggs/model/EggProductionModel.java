package pe.edu.vallegrande.eggs.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table("egg_production")
public class EggProductionModel {

    @Id
    private Integer id;

    private Integer quantityEggs;
    private Integer eggsKilo;
    private BigDecimal priceKilo;
    private LocalDate registrationDate;
}
