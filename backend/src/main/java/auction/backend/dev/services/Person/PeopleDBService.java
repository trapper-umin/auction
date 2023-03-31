package auction.backend.dev.services.Person;

import auction.backend.dev.models.Person;
import auction.backend.dev.repositories.PeopleRepository;
import auction.backend.dev.services.common.ICommonService;
import auction.backend.dev.util.Excaption.common.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleDBService implements ICommonService<Person> {

    private final PeopleRepository peopleRepository;

    public PeopleDBService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    public List<Person>getAll(){
        List<Person>people=peopleRepository.findAll();
        if(people.size()==0)
            throw new NotFoundException("There are no people in the database");
        return people;
    }

    public Person get(int id){
        Optional<Person>person=peopleRepository.findById(id);
        if(person.isEmpty())
            throw new NotFoundException("Person with ID "+id+" not found");
        return person.get();
    }

    @Transactional
    public void create(Person person){
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person){
        Optional<Person>personFromDB=peopleRepository.findById(id);
        if(personFromDB.isEmpty())
            throw new NotFoundException("Person with ID "+id+" not found");
        person.setId(id);
        person.setCreatedAt(personFromDB.get().getCreatedAt());
        person.setUpdatedAt(LocalDateTime.now());
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        if(peopleRepository.findById(id).isEmpty())
            throw new  NotFoundException("Person with ID "+id+" not found");
        peopleRepository.deleteById(id);
    }
}
