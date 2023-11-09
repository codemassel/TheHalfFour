package ShopApp.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cities {

    public Cities() {
        //empty constructor für Spring
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zipcode_id")
    private long id;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name ="city", nullable = true)
    private String city;

    @OneToMany(mappedBy = "zipcode")
    private List<Customer> customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
