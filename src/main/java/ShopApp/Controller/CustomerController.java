package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Service.CitiesService;
import ShopApp.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/index")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    private final CustomerService customerService;
    private final CitiesService citiesService;

    public CustomerController(CustomerService customerService, CitiesService citiesService){
        this.customerService = customerService;
        this.citiesService = citiesService;
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
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public String getCustomers(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customersTest";
    }

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String returnIndex(Model model) {

        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public String login() {
        return "redirect:/index/";
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id, Model model) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
/*

    /*Waren hinzufügen
    @PostMapping("/createShopItem")
    public ResponseEntity<ShopItem> createShopItems(
            @RequestParam String shopItem,
            @RequestParam long price) {

        //business logic
    }

    @GetMapping("/customersByName/{firstName}{lastName}")
    public List<Customer> getCustomersByName(@RequestParam String firstName, @RequestParam String lastName) {
        return rep.findByFirstNameAndLastName(firstName, lastName);
    }
    */


}
