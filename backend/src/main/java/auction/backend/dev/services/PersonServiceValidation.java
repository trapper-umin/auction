package auction.backend.dev.services;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceValidation {

    private final PersonRepository personRepository;

    public PersonServiceValidation(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public Optional<Person> getByName(String name){
        return personRepository.findByName(name);
    }
}
