package ShopApp.Service;

import ShopApp.Model.Cities;
import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CitiesRepository citiesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository, citiesRepository, passwordEncoder);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        Cities zipcode = new Cities();
        zipcode.setZipcode("12345");
        zipcode.setCity("TestCity");
        customer.setZipcode(zipcode);
        customer.setPassword("password");

        Cities city = new Cities();
        city.setCity("TestCity");
        city.setZipcode("12345");

        when(citiesRepository.findByZipcode("12345")).thenReturn(null);
        when(citiesRepository.save(Mockito.any(Cities.class))).thenReturn(city);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer createdCustomer = customerService.createCustomer(customer);

        assertEquals(customer, createdCustomer);
    }
}
