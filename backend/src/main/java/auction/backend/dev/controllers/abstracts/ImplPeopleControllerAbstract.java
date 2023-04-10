package auction.backend.dev.controllers.abstracts;

import auction.backend.dev.controllers.interfaces.IPeopleController;
import auction.backend.dev.dto.PersonDTO;
import auction.backend.dev.dto.PersonDTOResponse;
import auction.backend.dev.services.Person.PeopleService;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@Tag(name = "People",description = "A REST controller that allows you to commit with the people of this site")
public abstract class ImplPeopleControllerAbstract implements IPeopleController {

    private final PeopleService peopleService;

    public ImplPeopleControllerAbstract(PeopleService peopleService){
        this.peopleService=peopleService;
    }

    @Override
    public ResponseEntity<ResponseDTO<PersonDTOResponse>> getAll() {
        return peopleService.getAll();
    }

    @Override
    public ResponseEntity<ResponseDTO<PersonDTO>> get(int id) {
        return peopleService.get(id);
    }

    @Override
    public ResponseEntity<ResponseDTO<PersonDTO>> create(PersonDTO personDTO, BindingResult bindingResult) {
        return peopleService.create(personDTO,bindingResult);
    }

    @Override
    public ResponseEntity<ResponseDTO<PersonDTO>> update(int id, PersonDTO personDTO, BindingResult bindingResult) {
        return peopleService.update(id,personDTO,bindingResult);
    }

    @Override
    public ResponseEntity<GoodResponse> delete(int id) {
        return peopleService.delete(id);
    }
}
