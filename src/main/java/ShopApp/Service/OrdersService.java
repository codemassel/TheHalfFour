package ShopApp.Service;

import ShopApp.Model.Orders;
import ShopApp.Repository.OrdersRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders updateOrders(Orders ordersToUpdate) {
        if (!ordersRepository.existsById(ordersToUpdate.getId())) {
            throw new IllegalArgumentException("Order mit der ID " + ordersToUpdate.getId() + " existiert nicht.");
        }

        Orders existingOrder = ordersRepository.findById(ordersToUpdate.getId()).orElseThrow(() ->
                new IllegalArgumentException("Order mit der ID " + ordersToUpdate.getId() + " existiert nicht."));

        existingOrder.setCustomer(ordersToUpdate.getCustomer());
        existingOrder.setShopitems(ordersToUpdate.getShopitems());
        existingOrder.setPriority(ordersToUpdate.getPriority());
        existingOrder.setStatus(ordersToUpdate.getStatus());
        existingOrder.setDiscount(ordersToUpdate.getDiscount());
        existingOrder.setCreationDate(ordersToUpdate.getCreationDate());

        return ordersRepository.save(existingOrder);
    }

}
