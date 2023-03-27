package auction.backend.dev.controllers;

import auction.backend.dev.controllers.abstracts.ImplPeopleControllerAbstract;
import auction.backend.dev.services.PeopleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2.0/people")
public class PeopleController extends ImplPeopleControllerAbstract {

    public PeopleController(PeopleService peopleService){
        super(peopleService);
    }
}
