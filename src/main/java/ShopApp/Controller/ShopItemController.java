package ShopApp.Controller;

import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class ShopItemController {

    @Autowired
    ShopItemRepository itemRep;



}
