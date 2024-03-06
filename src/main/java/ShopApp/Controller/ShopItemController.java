package ShopApp.Controller;

import ShopApp.Model.Orders;
import ShopApp.Model.ShopItem;
import ShopApp.Repository.ShopItemRepository;
import ShopApp.Service.ShopItemService;
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

    private final ShopItemRepository shopItemRepository;
    private final ShopItemService shopItemService;

    public ShopItemController(ShopItemRepository itemRep, ShopItemService shopItemService) {
        this.shopItemRepository = itemRep;
        this.shopItemService = shopItemService;
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
        String imageData = shopItem.getImage();
        model.addAttribute("ShopItem", shopItem);
        model.addAttribute("order", new Orders());
        model.addAttribute("imageData", imageData);
        return new ModelAndView("productpage");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/updateShopItem")
    public ModelAndView updateShopitem(Model model, @ModelAttribute ShopItem shopitem) {
        model.addAttribute("shopItem", shopitem);
        shopItemService.updateShopItem(shopitem);

        return new ModelAndView("redirect:/adminpanel"); //vorher "index"
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/createshopitem")
    public ModelAndView getShopItemPage(Model model) {

        model.addAttribute("shopItem", new ShopItem());
        return new ModelAndView("createproduct");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/createShopItem", method = RequestMethod.POST)
    public ModelAndView createShopItem(Model model, @ModelAttribute("shopItem") ShopItem shopItem) {

        shopItemService.createShopItem(shopItem);

        return new ModelAndView("index");
    }

}
