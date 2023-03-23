package auction.backend.dev.services;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    public List<Person> getPeople(){
        return peopleRepository.findAll();
    }
}
