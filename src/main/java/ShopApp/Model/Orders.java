package ShopApp.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {

    public Orders() {
        //empty constructor für Spring
    }

    public Orders(Customer customer) {
        this.customer = customer;
    }

    public Orders(long id, Customer customer, int priority, Status status,
                  double totalPrice, double discount, Date creationDate) {
        this.id = id;
        this.customer = customer;
        this.priority = priority;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.creationDate = creationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Customer customer;

    /*
    @ManyToOne
    private List<ShopItem> shopitems;
    */

    @Column
    private int priority;

    @Column
    private Status status;

    @Column
    private double totalPrice;

    @Column
    private double discount;

    @Column
    private Date creationDate;

    @Column
    private Date shipmentDate;

    @Column
    private Date completionDate;


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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

}
