package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Model.Orders;
import ShopApp.Model.ShopItem;
import ShopApp.Model.Status;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Repository.OrdersRepository;
import ShopApp.Repository.ShopItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;
    private ShopItemRepository shopItemRepository;

    public OrdersController(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getOrders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> allOrders = ordersRepository.findAll();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getOrder")
    public ResponseEntity<List<Orders>> getOrder(@RequestParam(name = "customerId") Long customerId) {
        List<Orders> order = ordersRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrder(Model model, @ModelAttribute("orders") Orders orders, @RequestParam Long shopItemId, @RequestParam String emailId) {

        Orders newOrder = new Orders();

        Customer customer = customerRepository.findByEmailId(emailId);
        ShopItem shopItem = shopItemRepository.findById(shopItemId).orElse(null);;

        newOrder.setCustomer(customer);
        newOrder.setShopitems(shopItem);
        newOrder.setStatus(Status.RECEIVED);

        Date dateNow = new Date();
        newOrder.setCreationDate(dateNow);

        ordersRepository.save(orders);

        return "redirect:/index";
    }
}
