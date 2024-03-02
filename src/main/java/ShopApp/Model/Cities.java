package ShopApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cities {

    public Cities() {
        //empty constructor für Spring
    }
    @Id
    @Column(name = "zipcode",unique = true, nullable = false)
    private String zipcode;

    @Column(name ="city", nullable = false)
    private String city;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
