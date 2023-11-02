package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Transactional
public class CustomerController {

    @Autowired
    private CustomerRepository rep;

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
    */
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

}
