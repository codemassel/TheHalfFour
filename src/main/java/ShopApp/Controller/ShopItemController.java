package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        List<ShopItem> ShopItems = ;
        model.addAttribute("customers", customers);
        return "customersTest";
    }

}
