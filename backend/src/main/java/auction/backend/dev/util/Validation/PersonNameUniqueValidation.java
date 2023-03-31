package auction.backend.dev.util.Validation;

import auction.backend.dev.dto.PersonDTO;
import auction.backend.dev.models.Person;
import auction.backend.dev.services.Person.PeopleServiceValidation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonNameUniqueValidation implements Validator {

    private final PeopleServiceValidation peopleServiceValidation;

    public PersonNameUniqueValidation(PeopleServiceValidation peopleServiceValidation){
        this.peopleServiceValidation=peopleServiceValidation;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PersonDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO person=(PersonDTO) target;
        Optional<Person> personFromDB=peopleServiceValidation.getByName(person.getName());
        if(personFromDB.isPresent())
            errors.rejectValue("name","","This name already exist");
    }
}
