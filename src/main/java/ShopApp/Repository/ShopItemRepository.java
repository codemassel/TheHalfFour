package ShopApp.Repository;

import ShopApp.Model.ShopItem;
import org.springframework.data.repository.CrudRepository;

public interface ShopItemRepository extends CrudRepository<ShopItem, Long> {
}
