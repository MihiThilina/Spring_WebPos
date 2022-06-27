package lk.ijse.springpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class OrderDetailsDto {
    private String orderID;
    private String itemCode;
    private String orderqty;
    private double discount;
    private int balance;
}
