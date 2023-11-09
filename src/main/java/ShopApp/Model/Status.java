package ShopApp.Model;

public enum Status{
    RECEIVED("Order received"),
    PROCESSING("Order in process"),
    SHIPPED("Order shipped"),
    COMPLETED("Order completed");

    //Instanzvariable
    private final String state;

    Status(String state) {
        this.state = state;
    }
}
