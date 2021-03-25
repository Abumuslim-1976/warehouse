package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProductDto {

    private Double price;

    private Double amount;

    private Integer productId;

    private Integer outputId;
}
