package ShopApp.Service;

import ShopApp.Model.Customer;
import ShopApp.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){

        return (List<Customer>) customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
}
