package auction.backend.dev.services;

import auction.backend.dev.base.EntityTag;
import auction.backend.dev.dto.PersonDTO;
import auction.backend.dev.models.Person;
import auction.backend.dev.util.Excaption.common.ErrorInfo;
import auction.backend.dev.util.Excaption.common.NotCreatedException;
import auction.backend.dev.util.Excaption.common.NotUpdatedException;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import auction.backend.dev.util.Validation.PersonNameUniqueValidation;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {

    private final PeopleDBService peopleDBService;
    private final ModelMapper modelMapper;
    private final PersonNameUniqueValidation personNameUniqueValidation;

    public PeopleService(PeopleDBService peopleDBService,
                         ModelMapper modelMapper,
                         PersonNameUniqueValidation personNameUniqueValidation){
        this.peopleDBService=peopleDBService;
        this.modelMapper = modelMapper;
        this.personNameUniqueValidation = personNameUniqueValidation;
    }

    public ResponseEntity<ResponseDTO<PersonDTO>>getAll(){
        List<Person>people=peopleDBService.getAll();
        List<PersonDTO>peopleDTOS=new ArrayList<>();
        for (Person person : people)
            peopleDTOS.add(convertToPersonDTO(person));

        ResponseDTO<PersonDTO>response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.PERSON,
                LocalDateTime.now(),
                peopleDTOS
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PersonDTO>>get(int id){
        PersonDTO person=convertToPersonDTO(peopleDBService.get(id));

        ResponseDTO<PersonDTO>response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.PERSON,
                LocalDateTime.now(),
                wrapPersonDTO(person)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PersonDTO>>create(PersonDTO personDTO,
                                                        BindingResult bindingResult){
        validation("create",personDTO,bindingResult);
        peopleDBService.create(convertToPerson(personDTO));
        ResponseDTO<PersonDTO>response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.PERSON,
                LocalDateTime.now(),
                wrapPersonDTO(personDTO)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<PersonDTO>>update(int id,
                                                        PersonDTO personDTO,
                                                        BindingResult bindingResult){
        validation("update",personDTO,bindingResult);
        peopleDBService.update(id,convertToPerson(personDTO));
        ResponseDTO<PersonDTO>response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.PERSON,
                LocalDateTime.now(),
                wrapPersonDTO(personDTO)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<GoodResponse>delete(int id){
        peopleDBService.delete(id);
        GoodResponse response=new GoodResponse(
                "Person with ID " + id + " was deleted",
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person,PersonDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    private List<PersonDTO>wrapPersonDTO(PersonDTO personDTO){
        List<PersonDTO>wrapper=new ArrayList<>();
        wrapper.add(personDTO);
        return wrapper;
    }

    private void validation(String operation,PersonDTO personDTO,BindingResult bindingResult){
        personNameUniqueValidation.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors()){
            List<ErrorInfo>info=new ArrayList<>();
            List<FieldError>errors=bindingResult.getFieldErrors();
            for(FieldError error : errors)
                info.add(new ErrorInfo(error.getField(),error.getDefaultMessage()));
            if(operation.equals("create"))
                throw new NotCreatedException(info);
            else throw new NotUpdatedException(info);
        }
    }
}
