package lk.ijse.springpos.controller;

import lk.ijse.springpos.dto.OrderDto;
import lk.ijse.springpos.service.PurchaseOrderService;
import lk.ijse.springpos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchase_Order")
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService poService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllOrders() {
        return new ResponseUtil(200, "Ok", poService.getAllOrders());
    }

    @ResponseStatus(HttpStatus.CREATED) //201
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil purchaseOrder(@RequestBody OrderDto ordersDTO) {
        System.out.println(ordersDTO.toString());
        poService.purchaseOrder(ordersDTO);
        return new ResponseUtil(200, "Save", null);
    }

}
