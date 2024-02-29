package ShopApp.Repository;

import ShopApp.Model.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {

    List<Orders> findAll();
    List<Orders> findByCustomerId(Long customerId);

}
