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
    private CitiesRepository citiesRepository;
    private final CustomerService customerService;
    private final CitiesService citiesService;
    private final PasswordService passwordService;
    private final PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepository, CitiesRepository citiesRepository, CustomerService customerService,
                              CitiesService citiesService, PasswordService passwordService, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.citiesRepository = citiesRepository;
        this.customerService = customerService;
        this.citiesService = citiesService;
        this.passwordService = passwordService;
        this.passwordEncoder = passwordEncoder;
    }
/*
    public void createCustomer(String firstName, String lastName, String emailId, String password, String zipcodeValue, String city){
        Cities zipcode = new Cities();
        zipcode.setZipcode(zipcodeValue);
        zipcode.setCity(city);
        zipRep.save(zipcode);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmailId(emailId);
        customer.setPassword(password);
        customer.setZipcode(zipcode);
        rep.save(customer);
    }*/

    // localhost:8888/index/
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customersTest";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute Customer customer) {

        customerService.createCustomer(customer);

        // Konvertiere das Cities-Objekt in ein JSON-String
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(customer);
            System.out.println("JSON-Inhalt: " + json);
        } catch (Exception e) {
            // Behandele etwaige Ausnahmen hier
            e.printStackTrace();
        }

        return "redirect:/index";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String returnIndex(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "index";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public String login() {
        return "redirect:/index/";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerById(@RequestParam(name = "customerId") Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public ResponseEntity<Optional<Customer>> getCustomer(@RequestParam(name = "customerId") Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/changetestpw", method = {RequestMethod.GET, RequestMethod.PATCH})
    public ResponseEntity<Customer> encryptTestPassword(@RequestParam(name = "customerId") Long customerId) {

        Customer customer = customerRepository.getById(customerId);
        String unencodedPassword = customer.getPassword();
        String encodedPassword = passwordService.encodePassword(unencodedPassword);
        customer.setPassword(encodedPassword);
        Customer updatedCustomer = customerRepository.save(customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

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


