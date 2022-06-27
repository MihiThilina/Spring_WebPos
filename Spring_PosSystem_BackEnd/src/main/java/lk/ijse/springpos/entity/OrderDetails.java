package lk.ijse.springpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@IdClass(OrderItem_PK.class)
public class OrderDetails {
    @Id
    private String orderID;
    @Id
    private String itemCode;
    private String orderqty;
    private double discount;
    private int balance;

    @ManyToOne
    @JoinColumn(referencedColumnName = "orderID",insertable = false,updatable = false)
    private Orders orders;

    //Out-verse
    @ManyToOne
    @JoinColumn(referencedColumnName = "itemCode",insertable = false,updatable = false)
    private Item items;
}
