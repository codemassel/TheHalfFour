package ShopApp.Controller;

import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/yachts")
public class ShopItemController {

    ShopItemRepository shopItemRepository;

    public ShopItemController(ShopItemRepository itemRep) {
        this.shopItemRepository = itemRep;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<ShopItem>> getAllShopitems() {
        List<ShopItem> shopItems = shopItemRepository.findAll();
        return new ResponseEntity<>(shopItems, HttpStatus.OK);
    }

}
