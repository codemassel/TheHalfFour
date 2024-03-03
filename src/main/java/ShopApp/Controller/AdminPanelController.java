package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminPanelController {

    private final CustomerService customerService;

    public AdminPanelController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/adminpanel")
    public String getAdminPanel(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "adminpanel";
    }
}
