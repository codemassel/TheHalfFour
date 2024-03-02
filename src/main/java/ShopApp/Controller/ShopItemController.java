package ShopApp.Controller;

import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

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

        List<ShopItem> ShopItems = shopItemRepository.findAll();
        model.addAttribute("shopItems", ShopItems);
        return "customersTest";
    }

    @GetMapping("/{id}")
    public ModelAndView getShopItemById(@PathVariable Long id, Model model) {
        ShopItem shopItem = shopItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ShopItem not found"));
        model.addAttribute("ShopItem", shopItem);
        return new ModelAndView("productpage");
    }


}
