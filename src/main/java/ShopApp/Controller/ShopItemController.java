package ShopApp.Controller;

import ShopApp.Repository.ShopItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Transactional
public class ShopItemController {

    @Autowired
    ShopItemRepository itemRep;



}
