package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Model.Orders;
import ShopApp.Model.ShopItem;
import ShopApp.Repository.OrdersRepository;
import ShopApp.Repository.ShopItemRepository;
import ShopApp.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminPanelController {

    private final CustomerService customerService;
    private final ShopItemRepository shopItemRepository;

    private final OrdersRepository ordersRepository;


    public AdminPanelController(CustomerService customerService, ShopItemRepository shopItemRepository, OrdersRepository ordersRepository) {
        this.customerService = customerService;
        this.shopItemRepository = shopItemRepository;
        this.ordersRepository = ordersRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/adminpanel")
    public String getAdminPanel(Model model) {
        List<Customer> customers = customerService.getCustomers();
        List<ShopItem> shopItems = shopItemRepository.findAll();
        List<Orders> orders = ordersRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("shopItems", shopItems);
        model.addAttribute("orders", orders);
        model.addAttribute("customer", new Customer());
        model.addAttribute("shopItem", new ShopItem());
        model.addAttribute("order", new Orders());
        return "adminpanel";
    }
}
