package Controller;

import Model.Customer;
import Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository rep;

    @GetMapping("/customers")
    public void printCustomer() {

        Iterable<Customer> allCustomers = rep.findAll();

        Iterator<Customer> iterator = allCustomers.iterator();

        while(iterator.hasNext()) {
            System.out.println();
        }
    }
}
