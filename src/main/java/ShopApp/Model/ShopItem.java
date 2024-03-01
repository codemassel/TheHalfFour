package ShopApp.Model;

import jakarta.persistence.*;

@Entity(name = "shopitem")
public class ShopItem {

    public ShopItem() {
        //empty constructor for spring
    }

    public ShopItem(long id, String itemName, String itemPrice, String description,  byte[] image) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.description = description;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "itemPrice", nullable = false)
    private String itemPrice;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "isVisible")
    private boolean isVisible;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isVisible() { return isVisible; }

    public void setVisible(boolean visible) { isVisible = visible; }

}
