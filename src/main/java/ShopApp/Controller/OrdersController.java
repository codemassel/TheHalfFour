package ShopApp.Controller;

import ShopApp.Model.Orders;
import ShopApp.Repository.OrdersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;

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
}
