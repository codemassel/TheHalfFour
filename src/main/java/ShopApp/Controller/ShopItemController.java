package ShopApp.Controller;

import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getShopItems(Model model) {

        List<ShopItem> shopItems = shopItemRepository.findAll();
        model.addAttribute("shopItems", shopItems);
        return "customersTest";
    }

}
