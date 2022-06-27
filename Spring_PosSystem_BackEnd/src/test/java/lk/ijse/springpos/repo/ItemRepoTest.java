package lk.ijse.springpos.repo;

import lk.ijse.springpos.config.JPAConfig;
import lk.ijse.springpos.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration // State test configuration class
@ContextConfiguration(classes = {JPAConfig.class}) // import configurations for Test Context
@ExtendWith(SpringExtension.class)
class ItemRepoTest {

    @Autowired
    ItemRepo itemRepo;

    @Test // Test method
    public void saveItem() {
        //Ok let's save a customer using Customer Repo
        Item item1 = new Item("I001", "Eggs", 100.00, 10);
        itemRepo.save(item1);
    }

}