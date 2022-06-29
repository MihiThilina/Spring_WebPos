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
        Orders orders = mapper.map(dto, Orders.class);
        if(!ordersRepo.existsById(dto.getOrderID())){
            ordersRepo.save(orders);
            if(dto.getOrderDetails().size() < 1)throw new RuntimeException("No items added for the order..!");
            for(OrderDetails details:orders.getOrderDetails()){
                Item item = itemRepo.findById(details.getItemCode()).get();
                item.setQty(item.getQty()-details.getOrderqty());
                itemRepo.save(item);
            }
        }else {
            throw new RuntimeException("Purchase Order Failed..!, Order ID " + dto.getOrderID() + " Already Exist.!");
        }
    }

    @Override
    public void deleteOrder(String oid) {

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
