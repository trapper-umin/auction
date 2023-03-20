package auction.backend.dev.controllers;

import auction.backend.dev.util.Excaption.Person.PeopleNotFoundException;
import auction.backend.dev.util.PeopleDTOResponse;
import auction.backend.dev.dto.PersonDTO;
import auction.backend.dev.models.Person;
import auction.backend.dev.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "test/v1")
public class TestRESTController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    public TestRESTController(PersonService personService,
                              ModelMapper modelMapper){
        this.personService=personService;
        this.modelMapper=modelMapper;
    }

    @GetMapping("/people")
    public ResponseEntity<PeopleDTOResponse> get(){
        List<Person> people=personService.getPeople();
        if(people.size()==0)
            throw new PeopleNotFoundException();
        List<PersonDTO> peopleDTO=new ArrayList<>();
        for (Person person : people) {
            peopleDTO.add(convertToPersonDTO(person));
        }
        PeopleDTOResponse response=new PeopleDTOResponse(LocalDateTime.now(),peopleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person,PersonDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO,Person.class);
    }
}