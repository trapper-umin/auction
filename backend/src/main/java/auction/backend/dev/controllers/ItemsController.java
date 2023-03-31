package auction.backend.dev.controllers;

import auction.backend.dev.controllers.abstracts.ImplItemsControllerAbstract;
import auction.backend.dev.util.Excaption.Item.ItemsExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ItemsExceptionHandler
@RequestMapping("api/v1.0/items")
public class ItemsController extends ImplItemsControllerAbstract {
}
