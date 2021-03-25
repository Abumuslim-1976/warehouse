package uz.pdp.warehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDto {

    private Timestamp timestamp;

    private String factureNumber;

    private Integer warehouseId;

    private Integer currencyId;

    private Integer clientId;

}
