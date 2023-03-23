package auction.backend.dev.controllers;

import auction.backend.dev.controllers.abstracts.ImplCreatorsControllerAbstract;
import auction.backend.dev.services.CreatorsService;
import auction.backend.dev.util.Excaption.Creator.CreatorsExceptionHandler;
import org.springframework.web.bind.annotation.*;


@RestController
@CreatorsExceptionHandler
@RequestMapping("api/v1.0/creators")
public class CreatorsController extends ImplCreatorsControllerAbstract{

    public CreatorsController(CreatorsService creatorsService) {
        super(creatorsService);
    }
}
