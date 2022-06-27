package lk.ijse.springpos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDto {
    private String orderID;
    private String  orderDate;
    private CustomerDto  custID;
    private ArrayList<OrderDetailsDto> orderDetailsDTOS;

}
