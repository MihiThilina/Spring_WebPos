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
    private String itemCode;
    private String orderID;
    private int balance;
    private double discount;
    private String orderqty;


}
