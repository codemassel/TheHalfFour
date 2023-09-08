package Repository;

import Model.Customer;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public void run(String... args) {
        // Kunden erstellen und in die Datenbank speichern
        customerRepository.save(new Customer("Hans", "Sarpei", "sarpei@gmail.com"));
        customerRepository.save(new Customer("Daniel", "Blijad", "blijad@gmail.com"));
        customerRepository.save(new Customer("Mike", "Rohsoft", "rohsoft@gmail.com"));
        customerRepository.save(new Customer("Tony", "Stark", "stark@gmail.com"));

        // Abfragen der Kunden
        long customerCount = customerRepository.count();
        logger.info("# of customers: {}", customerCount);

        logger.info("All customers:");
        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> logger.info("{}", customer.toString()));

        logger.info("------------------------");

        // Löschen eines Kunden mit einer bestimmten ID
        long customerIdToDelete = 1L;
        customerRepository.deleteById(customerIdToDelete);

        logger.info("Deleted customer with ID {}", customerIdToDelete);

        // Abfragen der verbleibenden Kundenanzahl
        long remainingCustomerCount = customerRepository.count();
        logger.info("# of remaining customers: {}", remainingCustomerCount);
    }
}