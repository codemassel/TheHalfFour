package ShopApp.Repository;

import ShopApp.Model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitiesRepository extends JpaRepository<Cities, Long> {
    Cities findByZipcode(String zipcode);
}
