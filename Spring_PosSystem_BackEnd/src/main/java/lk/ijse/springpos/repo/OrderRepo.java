package lk.ijse.springpos.repo;

import lk.ijse.springpos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,String> {
}
