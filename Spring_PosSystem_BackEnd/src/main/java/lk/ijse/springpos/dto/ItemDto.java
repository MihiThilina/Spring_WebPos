package lk.ijse.springpos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ItemDto {
    private String ItemCode;
    private String ItemName;
    private double Price;
    private int  Qty;
}
