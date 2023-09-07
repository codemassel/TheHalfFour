import Model.Customer;
import Repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    //Autowired = holt Bean aus CustomerRepository
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String...args) {

        customerRepository.save(new Customer("Hans", "Sarpei", "sarpei@gmail.com"));
        customerRepository.save(new Customer("Daniel", "Blijad", "blijad@gmail.com"));
        customerRepository.save(new Customer("Mike", "Rohsoft", "rohsoft@gmail.com"));
        customerRepository.save(new Customer("tony", "stark", "stark@gmail.com"));

        //Abfragen der Employees
        //Anzahl der Employees mit count
        logger.info("# of customer: {}", customerRepository.count());

        logger.info("All customers:");
        Iterable <Customer> customers = customerRepository.findAll();

        Iterator<Customer> iterator = customers.iterator();
        while(iterator.hasNext()) {
            logger.info("{}", iterator.next().toString());
        }

        logger.info("------------------------");

        logger.info("Deleting employee with id 1");
        customerRepository.deleteById(1L);

        logger.info("# of customers: {}", customerRepository.count());

        customerRepository.existsById(2L);
        customerRepository.findById(2L);
    }
}