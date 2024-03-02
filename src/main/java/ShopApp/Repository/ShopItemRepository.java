package ShopApp.Repository;

import ShopApp.Model.ShopItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopItemRepository extends CrudRepository<ShopItem, Long> {
    List<ShopItem> findAll();

    Optional<ShopItem> findById(Long id);
}
