package ShopApp.Service;

import ShopApp.Model.Cities;
import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CitiesRepository citiesRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, CitiesRepository citiesRepository, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.citiesRepository = citiesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * gets every customer
     * @return
     */
    public List<Customer> getCustomers(){
        return (List<Customer>) customerRepository.findAll();
    }

    /**
     * Creates new Customer after encrypting the password
     * @param customer
     * @return
     */
    public Customer createCustomer(Customer customer){
        Cities city = getOrCreateCityByZipcode(customer.getZipcode().getZipcode());
        city.setCity(customer.getZipcode().getCity());
        customer.setZipcode(city);
        customer.setAdmin(false);
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);

        return customerRepository.save(customer);
    }

    /**
     * gets every City with the corresponding zipcode
     * @param zipcode
     * @return
     */
    private Cities getOrCreateCityByZipcode(String zipcode) {
        Cities existingCity = citiesRepository.findByZipcode(zipcode);
        if (existingCity != null) {
            return existingCity;
        } else {
            Cities newCity = new Cities();
            newCity.setZipcode(zipcode);
            return newCity;
        }
    }

    public Customer updateCustomer(Customer customerToUpdate) {
        System.out.println(customerToUpdate.getFirstName() + " " + customerToUpdate.getId() + " amk hahahahaha");
        if (!customerRepository.existsById(customerToUpdate.getId())) {
            throw new IllegalArgumentException("Kunde mit der ID " + customerToUpdate.getId() + " existiert nicht.");
        }

        Customer existingCustomer = customerRepository.findById(customerToUpdate.getId()).orElseThrow(() ->
                new IllegalArgumentException("Kunde mit der ID " + customerToUpdate.getId() + " existiert nicht."));

        Cities city = getOrCreateCityByZipcode(customerToUpdate.getZipcode().getZipcode());
        city.setCity(customerToUpdate.getZipcode().getCity());

        existingCustomer.setZipcode(city);
        existingCustomer.setEmailId(customerToUpdate.getEmailId());
        existingCustomer.setFirstName(customerToUpdate.getFirstName());
        existingCustomer.setLastName(customerToUpdate.getLastName());
        existingCustomer.setStreet(customerToUpdate.getStreet());
        existingCustomer.setHouseNumber(customerToUpdate.getHouseNumber());

        return customerRepository.save(existingCustomer);
    }
}
