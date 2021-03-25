package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {

    private Timestamp timestamp;

    private Double amount;

    private Double price;

    private Integer productId;

    private Integer inputId;

}
