package ShopApp.Service;

import ShopApp.Model.Cities;
import ShopApp.Model.Customer;
import ShopApp.Repository.CitiesRepository;
import ShopApp.Repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CitiesRepository citiesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CustomerService customerService;

    private final String EXPECTED_FIRSTNAME = "John";
    private final String EXPECTED_LASTNAME = "Doe";
    private final String EXPECTED_EMAIL = "john@example.com";
    private final String EXPECTED_UNENCODED_PASSWORD = "password";
    private final String ENCODED_PASSWORD = "encodedpassword";
    private final String EXPECTED_ZIPCODE = "12345";
    private final String EXPECTED_CITY = "TestCity";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository, citiesRepository, passwordEncoder);
    }

    /**
     * tests createCustomer()
     * Creates a new city and customer, tests if these values are getting saved
     * Expects new created customer-object and actual customer being returned being equal
     *
     */
    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(EXPECTED_FIRSTNAME);
        customer.setLastName(EXPECTED_LASTNAME);
        customer.setEmailId(EXPECTED_EMAIL);
        customer.setPassword(EXPECTED_UNENCODED_PASSWORD);

        Cities city = new Cities();
        city.setCity(EXPECTED_CITY);
        city.setZipcode(EXPECTED_ZIPCODE);
        customer.setZipcode(city);

        // sets mock-behaviour
        when(citiesRepository.findByZipcode(EXPECTED_ZIPCODE)).thenReturn(city);
        when(passwordEncoder.encode(EXPECTED_UNENCODED_PASSWORD)).thenReturn(ENCODED_PASSWORD);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer createdCustomer = customerService.createCustomer(customer);

        // Assert
        assertEquals(EXPECTED_FIRSTNAME, createdCustomer.getFirstName());
        assertEquals(EXPECTED_LASTNAME, createdCustomer.getLastName());
        assertEquals(EXPECTED_EMAIL, createdCustomer.getEmailId());
        assertEquals(ENCODED_PASSWORD, createdCustomer.getPassword());

        // Überprüfen, ob die Methoden auf den Mock-Objekten aufgerufen wurden
        verify(citiesRepository, times(1)).findByZipcode(EXPECTED_ZIPCODE);
        verify(passwordEncoder, times(1)).encode(EXPECTED_UNENCODED_PASSWORD);
        verify(customerRepository, times(1)).save(customer);
    }
}
