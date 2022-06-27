package lk.ijse.springpos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDto {
    private String custID;
    private String custName;
    private String custAddress;
    private double salary;
}
