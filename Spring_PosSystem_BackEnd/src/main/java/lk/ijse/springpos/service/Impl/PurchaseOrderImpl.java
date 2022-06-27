package lk.ijse.springpos.service.Impl;

import lk.ijse.springpos.dto.OrderDto;
import lk.ijse.springpos.entity.Item;
import lk.ijse.springpos.entity.OrderDetails;
import lk.ijse.springpos.entity.OrderItem_PK;
import lk.ijse.springpos.entity.Orders;
import lk.ijse.springpos.repo.ItemRepo;
import lk.ijse.springpos.repo.OrderDetailsRepo;
import lk.ijse.springpos.repo.OrderRepo;
import lk.ijse.springpos.service.PurchaseOrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseOrderImpl implements PurchaseOrderService {


    @Autowired
    private OrderRepo ordersRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper mapper;


    @Override
    public void purchaseOrder(OrderDto dto) {
        if (ordersRepo.existsById(dto.getOrderID())) {

            Orders order = mapper.map(dto, Orders.class);
            if (dto.getOrderDetailsDTOS().size() < 1) throw new RuntimeException("No items added for the order..!");

            for (OrderDetails od : order.getOrderDetails()) {
                Item item = itemRepo.findById(od.getItemCode()).get();
                OrderDetails previous = orderDetailsRepo.findById(String.valueOf(new OrderItem_PK(od.getOrderID(), od.getItemCode()))).get();

                //Update the Item Qty
                int newQty = Integer.parseInt(od.getOrderqty());
                int prevQty = Integer.parseInt(previous.getOrderqty());
                if (newQty > prevQty) {
                    int dif = newQty - prevQty;
                    item.setQty(item.getQty() - dif);
                } else if (newQty < prevQty) {
                    int dif = prevQty - newQty;
                    item.setQty(item.getQty() + dif);
                }
                itemRepo.save(item);
            }
            //then delete the old order
            ordersRepo.deleteById(dto.getOrderID());
            //finally update the new order
            ordersRepo.save(order);
        } else {
            throw new RuntimeException("Update Order Failed..!, Order ID " + dto.getOrderID() + " Not Exist.!");
        }
    }

    @Override
    public void deleteOrder(String oid) {
        if (ordersRepo.existsById(oid)) {
            ordersRepo.deleteById(oid);
        } else {
            throw new RuntimeException("Delete Order Failed..!, Order ID " + oid + " Not Exist..!");
        }
    }

    @Override
    public void updateOrder(OrderDto dto) {

    }

    @Override
    public OrderDto searchOrder(String oid) {
        return null;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return mapper.map(ordersRepo.findAll(), new TypeToken<List<OrderDto>>() {
        }.getType());
    }
}
