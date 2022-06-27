package lk.ijse.springpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Item {
    @Id
    private String ItemCode;
    private String ItemName;
    private double Price;
    private int  Qty;
}
