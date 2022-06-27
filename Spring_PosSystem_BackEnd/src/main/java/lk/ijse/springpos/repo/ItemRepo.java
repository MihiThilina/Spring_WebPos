package lk.ijse.springpos.repo;

import lk.ijse.springpos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,String> {
}
