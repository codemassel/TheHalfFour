package ShopApp.Controller;

import ShopApp.Model.Customer;
import ShopApp.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class CustomerController {

    @Autowired
    private CustomerRepository rep;

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

}
