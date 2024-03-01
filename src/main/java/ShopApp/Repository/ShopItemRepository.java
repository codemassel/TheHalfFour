package ShopApp.Repository;

import ShopApp.Model.ShopItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopItemRepository extends CrudRepository<ShopItem, Long> {
    List<ShopItem> findAll();
}
