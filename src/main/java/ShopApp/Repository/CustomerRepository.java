package ShopApp.Repository;

import ShopApp.Model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    Customer getById(Long id);
    Customer findByEmailId(String email);
}
