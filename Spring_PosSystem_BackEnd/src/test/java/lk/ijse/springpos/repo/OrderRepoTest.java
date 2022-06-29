package lk.ijse.springpos.repo;

import lk.ijse.springpos.config.JPAConfig;
import lk.ijse.springpos.dto.OrderDetailsDto;
import lk.ijse.springpos.entity.Customer;
import lk.ijse.springpos.entity.Item;
import lk.ijse.springpos.entity.OrderDetails;
import lk.ijse.springpos.entity.Orders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@ContextConfiguration(classes = {JPAConfig.class})
@ExtendWith(SpringExtension.class)
class OrderRepoTest {

    @Autowired
    OrderRepo orderRepo;



}