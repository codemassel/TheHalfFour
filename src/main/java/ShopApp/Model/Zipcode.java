package ShopApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Zipcode {

    public Zipcode() {
        //empty constructor für Spring
    }

    @Id
    @OneToOne
    private long zipcode;

    @Column(name ="city")
    private String city;
}
