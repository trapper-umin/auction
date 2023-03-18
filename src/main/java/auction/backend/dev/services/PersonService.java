package auction.backend.dev.services;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public List<Person> getPeople(){
        return personRepository.findAll();
    }
}
