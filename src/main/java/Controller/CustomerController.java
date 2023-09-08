package Controller;

import Model.Customer;
import Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository rep;

    @GetMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        Iterable<Customer> allCustomers = rep.findAll();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }
}
