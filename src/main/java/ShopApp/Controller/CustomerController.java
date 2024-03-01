package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Service.CitiesService;
import ShopApp.Service.CustomerService;
import ShopApp.Service.PasswordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
@RequestMapping("/index")
public class CustomerController {

    private CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final PasswordService passwordService;
    private final PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepository, CitiesRepository citiesRepository, CustomerService customerService,
                              CitiesService citiesService, PasswordService passwordService, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.passwordService = passwordService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Gets every Customer
     * Endpoint: ../index/customers
     * @param model
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customersTest";
    }

    /**
     * Created User and posts it, password gets decrypted in the corresponding service class
     * Endpoint: ../index/register
     * @param model
     * @param customer
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute Customer customer) {

        customerService.createCustomer(customer);

        // Converts the cities-Object into json and prints it
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(customer);
            System.out.println("JSON-Inhalt: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    /**
     * Open index.html if the endpoint gets requested
     * Endpoint: ../index
     * @param model
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String returnIndex(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "index";
    }

    /**
     * Method for the login
     * @return
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public String login(Model model, @ModelAttribute Customer customer) {

        Customer existingCustomer = customerRepository.findByEmailId(customer.getEmailId());
        if (existingCustomer != null && passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
            return "redirect:/index";
        } else {
            return "redirect:/login?error";
        }
    }

    /**
     * Gets the gustomer for corresponding params id and hands it to thymeleaf
     * Endpoint: ../index/customer
     * @param customerId
     * @param model
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/customer")
    public ResponseEntity<Optional<Customer>> getCustomerById(@RequestParam(name = "customerId") Long customerId, Model model) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        model.addAttribute("customer", customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public ResponseEntity<Boolean> checkifLoginDataCorrect(@RequestParam(name = "email") String email, @RequestParam(name = "pw") String pw) {
        Customer customer = customerRepository.findByEmailId(email);
        if (customer != null && passwordEncoder.matches(pw, customer.getPassword())) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
/*

    /*Waren hinzufügen
    @PostMapping("/createShopItem")
    public ResponseEntity<ShopItem> createShopItems(@RequestParam String shopItem,@RequestParam long price) {
        //business logic
    }

    @GetMapping("/customer")
    public List<Customer> getCustomersByName(@RequestParam (name="firstName") String firstName, @RequestParam (name = "lastName") String lastName) {
        return rep.findByFirstNameAndLastName(firstName, lastName);
    }
    */


