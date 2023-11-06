package ShopApp.Model;

import jakarta.persistence.*;

@Entity
public class Orders {

    public Orders() {
        //empty constructor für Spring
    }

    public Orders(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
