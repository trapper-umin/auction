package auction.backend.dev.util.Validation;

import auction.backend.dev.models.Person;
import auction.backend.dev.services.PersonServiceValidation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

public class PersonNameUniqueValidation implements Validator {

    private final PersonServiceValidation personServiceValidation;

    public PersonNameUniqueValidation(PersonServiceValidation personServiceValidation){
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
