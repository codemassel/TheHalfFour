package ShopApp.Model;

import jakarta.persistence.*;

@Entity
public class Priority {

    public Priority() {
        //empty constructor für Spring
    }

    public Priority (Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Customer customer;

    @OneToOne
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
