package lk.ijse.springpos.service;

import lk.ijse.springpos.dto.OrderDto;

import java.util.List;

public interface PurchaseOrderService {

    void purchaseOrder(OrderDto dto);
    void deleteOrder(String oid);
    void updateOrder(OrderDto dto);
    OrderDto searchOrder(String oid);
    List<OrderDto> getAllOrders();

}
