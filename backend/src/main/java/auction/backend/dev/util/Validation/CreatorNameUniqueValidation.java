package auction.backend.dev.util.Validation;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.services.Creator.CreatorsDBService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreatorNameUniqueValidation implements Validator {

    private final CreatorsDBService creatorsBDService;

    public CreatorNameUniqueValidation(CreatorsDBService creatorsBDService){
        this.creatorsBDService = creatorsBDService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CreatorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    CreatorDTO creator=(CreatorDTO) target;
    if(creatorsBDService.getOptionalCreatorByName(creator.getName()).isPresent())
        errors.rejectValue("name","","Name should be unique");
    }
}
