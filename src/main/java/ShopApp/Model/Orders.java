package ShopApp.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Orders {

    public Orders() {
        //empty constructor for Spring
    }

    public Orders(long id, Customer customer, ShopItem shopitems, int priority, Status status,
                  double discount, Date creationDate, Date shipmentDate, Date completionDate) {
        this.id = id;
        this.customer = customer;
        this.shopitems = shopitems;
        this.priority = priority;
        this.status = status;
        this.discount = discount;
        this.creationDate = creationDate;
        this.shipmentDate = shipmentDate;
        this.completionDate = completionDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "shopitems", referencedColumnName = "id")
    private ShopItem shopitems;

    @Column
    private int priority;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private double discount;

    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "UTC")
    private Date creationDate;

    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "UTC")
    private Date shipmentDate;

    @Column
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", timezone = "UTC")
    private Date completionDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShopItem getShopitems() {
        return shopitems;
    }

    public void setShopitems(ShopItem shopitems) {
        this.shopitems = shopitems;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
