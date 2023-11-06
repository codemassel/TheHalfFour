package ShopApp.Repository;

import ShopApp.Model.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {
    Zipcode findByZipcode(String zipcode);
}
