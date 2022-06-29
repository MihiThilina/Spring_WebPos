package lk.ijse.springpos.repo;

import lk.ijse.springpos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,String> {


}
