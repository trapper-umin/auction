package auction.backend.dev.util.Validation;

import auction.backend.dev.models.Person;
import auction.backend.dev.services.PeopleServiceValidation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonNameUniqueValidation implements Validator {

    private final PeopleServiceValidation personServiceValidation;

    public PersonNameUniqueValidation(PeopleServiceValidation personServiceValidation){
        this.personServiceValidation=personServiceValidation;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person=(Person) target;
        Optional<Person> personFromDB=personServiceValidation.getByName(person.getName());
        if(personFromDB.isPresent())
            errors.rejectValue("name","","This name already exist");
    }
}
