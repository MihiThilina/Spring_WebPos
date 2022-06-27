package lk.ijse.springpos.repo;

import lk.ijse.springpos.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,String> {
}
