package ShopApp.Service;

import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopItemService {

    private final ShopItemRepository shopItemRepository;

    public ShopItemService(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }

    public void updateShopItem(ShopItem shopItemToUpdate) {
        if (!shopItemRepository.existsById(shopItemToUpdate.getId())) {
            throw new IllegalArgumentException("Yacht mit der ID " + shopItemToUpdate.getId() + " existiert nicht.");
        }

        ShopItem existingShopItem = shopItemRepository.findById(shopItemToUpdate.getId()).orElseThrow(() ->
                new IllegalArgumentException("Yacht mit der ID " + shopItemToUpdate.getId() + " existiert nicht."));

        existingShopItem.setItemName(shopItemToUpdate.getItemName());
        existingShopItem.setItemPrice(shopItemToUpdate.getItemPrice());
        existingShopItem.setDescription(shopItemToUpdate.getDescription());

        shopItemRepository.save(existingShopItem);
    }
}