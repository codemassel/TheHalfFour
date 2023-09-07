package Repository;

import Model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    //Implementierung von CRUDRepositorygibt uns direkt Zugriff auf Methoden wie save(), findById(), findAll() usw
}