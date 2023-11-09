package ShopApp.Model;

import jakarta.persistence.Column;

public class Order_ShopItem {

    @Column(name ="order_id")
    private long order_id;

    @Column(name ="shopItemId")
    private long shopItemId;
}
