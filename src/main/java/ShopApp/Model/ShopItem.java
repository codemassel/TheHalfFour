package ShopApp.Model;

import jakarta.persistence.*;

@Entity(name = "shopitems")
public class ShopItem {

    public ShopItem() {
        //empty constructor für spring
    }

    public ShopItem(long id, String itemName, String itemPrice) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "itemPrice", nullable = false)
    private String itemPrice;

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

    @Override
    public String toString() {
        return "ShopItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                '}';
    }
}
