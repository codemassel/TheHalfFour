package ShopApp.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;


@Entity
public class Customer {

    public Customer() {
        //empty Constructor for Spring JPA
    }

    public Customer(String firstName, String lastName, String emailId, String password, Cities zipcode,
                    String street, int houseNumber, List<Orders> orders, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.zipcode = zipcode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.orders = orders;
        this.isAdmin = isAdmin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email_address")
    private String emailId;

    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zipcode") // updatable = false, insertable = false
    private Cities zipcode;

    @Column(name = "street")
    private String street;

    @Column(name ="house_number")
    private int houseNumber;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cities getZipcode() {
        return zipcode;
    }

    public void setZipcode(Cities zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public boolean isAdmin() {return isAdmin; }

    public void setAdmin(boolean admin) { isAdmin = admin; }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + " password=" + password + ", emailId=" + emailId + " cities=" + "]";
    }
}
