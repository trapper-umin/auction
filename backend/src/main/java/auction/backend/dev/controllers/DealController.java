package auction.backend.dev.controllers;

import auction.backend.dev.controllers.abstracts.ImplDealControllerAbstract;
import auction.backend.dev.services.DealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/deals")
public class DealController extends ImplDealControllerAbstract {

    public DealController(DealService dealService){
        super(dealService);
    }
}
