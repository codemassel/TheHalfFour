package ShopApp.Repository;

import ShopApp.Model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
}
