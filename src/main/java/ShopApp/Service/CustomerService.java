package ShopApp.Service;

import ShopApp.Model.Cities;
import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CitiesRepository citiesRepository;

    public CustomerService(CustomerRepository customerRepository, CitiesRepository citiesRepository){
        this.customerRepository = customerRepository;
        this.citiesRepository = citiesRepository;
    }

    public List<Customer> getCustomers(){

        return (List<Customer>) customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        Cities city = getOrCreateCityByZipcode(customer.getZipcode().getZipcode());
        city.setCity(customer.getZipcode().getCity());
        customer.setZipcode(city);
        return customerRepository.save(customer);
    }

    private Cities getOrCreateCityByZipcode(String zipcode) {
        Cities existingCity = citiesRepository.findByZipcode(zipcode);
        if (existingCity != null) {
            return existingCity;
        } else {
            Cities newCity = new Cities();
            newCity.setZipcode(zipcode);
            // Weitere Eigenschaften der Stadt setzen, falls erforderlich
            //citiesRepository.save(newCity);
            return newCity;
        }
    }
/*
    public void createCustomerTestAmk(String firstName, String lastName, String emailId, String password, String zipcodeValue, String city){
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
        customerRepository.save(customer);
    }*/
}
