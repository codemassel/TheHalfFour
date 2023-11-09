package ShopApp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;


@Entity
public class Customer {

    public Customer() {
        //empty Constructor für Spring JPA
    }

    public Customer(String firstName, String lastName, String emailId, String password, Cities zipcode,
                    String street, int houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.zipcode = zipcode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
    @JoinColumn(name = "zipcode")
    private Cities zipcode;

    @Column(name = "street")
    private String street;

    @Column(name ="house_number")
    private int houseNumber;

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

    @Override
    public String toString() {
        return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + " password=" + password + ", emailId=" + emailId + " cities=" + "]";
    }
}
