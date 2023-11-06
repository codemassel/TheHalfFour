package ShopApp.Service;

import ShopApp.Model.Customer;
import ShopApp.Repository.CustomerRepository;
import ShopApp.Repository.ZipcodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ZipcodeRepository zipRep;

    public CustomerService(CustomerRepository customerRepository, ZipcodeRepository zipRep){
        this.customerRepository = customerRepository;
        this.zipRep = zipRep;
    }

    public List<Customer> getCustomers(){

        return (List<Customer>) customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
/*
    public void createCustomerTestAmk(String firstName, String lastName, String emailId, String password, String zipcodeValue, String city){
        Zipcode zipcode = new Zipcode();
        zipcode.setZipcode(zipcodeValue);
        zipcode.setCity(city);
        zipRep.save(zipcode);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmailId(emailId);
        customer.setPassword(password);
        customer.setZipcode(zipcode);
        customerRepository.save(customer);
    }*/
}
