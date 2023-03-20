package auction.backend.dev.services;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleServiceValidation {

    private final PeopleRepository peopleRepository;

    public PeopleServiceValidation(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    public Optional<Person> getByName(String name){
        return peopleRepository.findByName(name);
    }
}
