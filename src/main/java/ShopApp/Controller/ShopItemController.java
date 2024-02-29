package ShopApp.Controller;

import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/yachts")
public class ShopItemController {

    ShopItemRepository itemRep;

    public ShopItemController(ShopItemRepository itemRep) {
        this.itemRep = itemRep;
    }



}
