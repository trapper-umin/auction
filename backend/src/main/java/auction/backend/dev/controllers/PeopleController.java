package auction.backend.dev.controllers;

import auction.backend.dev.controllers.abstracts.ImplPeopleControllerAbstract;
import auction.backend.dev.services.Person.PeopleService;
import auction.backend.dev.util.Excaption.Person.PeopleExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PeopleExceptionHandler
@RequestMapping("api/v1.0/people")
public class PeopleController extends ImplPeopleControllerAbstract {

    public PeopleController(PeopleService peopleService){
        super(peopleService);
    }
}
