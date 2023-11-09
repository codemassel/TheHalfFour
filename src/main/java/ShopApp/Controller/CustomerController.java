package ShopApp.Controller;

import ShopApp.Model.Cities;
import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Service.CustomerService;
import ShopApp.Service.ZipcodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/index")
public class CustomerController {

    @Autowired
    private CustomerRepository rep;

    @Autowired
    private CitiesRepository zipRep;

    private final CustomerService customerService;
    private final ZipcodeService zipcodeService;

    public CustomerController(CustomerService customerService, ZipcodeService zipcodeService){
        this.customerService = customerService;
        this.zipcodeService = zipcodeService;
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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute Customer customer) {

        customerService.createCustomer(customer);
        Cities cities = customer.getZipcode();
        zipRep.save(cities);

        // Konvertiere das Cities-Objekt in ein JSON-String
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(customer);
            System.out.println("JSON-Inhalt: " + json);
        } catch (Exception e) {
            // Behandele etwaige Ausnahmen hier
            e.printStackTrace();
        }

        return "redirect:/index/";
    }
/*
    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        Iterable<Customer> allCustomers = rep.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    //Kunde ersstellen
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email) {

        // Erstellen eines neuen Kunden
        Customer newCustomer = new Customer(firstName, lastName, email);

        // Speichern des Kunden in der Datenbank
        Customer savedCustomer = rep.save(newCustomer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    /*Waren hinzufügen
    @PostMapping("/createShopItem")
    public ResponseEntity<ShopItem> createShopItems(
            @RequestParam String shopItem,
            @RequestParam long price) {

        //business logic
    }

    @GetMapping("/customer/{id}")
    public String getCustomer(@PathVariable Long id, Model model) {
        Customer customer = rep.findById(id).orElse(null);
        if (customer != null) {
            model.addAttribute("customer", customer);
        }
        return "index"; // Name der HTML-Vorlage
    }

    @GetMapping("/customersByName/{firstName}{lastName}")
    public List<Customer> getCustomersByName(@RequestParam String firstName, @RequestParam String lastName) {
        return rep.findByFirstNameAndLastName(firstName, lastName);
    }
    */


}
