package auction.backend.dev.controllers.abstracts;

import auction.backend.dev.controllers.interfaces.ICreatorsController;
import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.services.Creator.CreatorsService;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Creators",description = "A REST controller that allows you to commit with the creators of this site")
public abstract class ImplCreatorsControllerAbstract implements ICreatorsController {

    private final CreatorsService creatorsService;
    public ImplCreatorsControllerAbstract(CreatorsService creatorsService){
        this.creatorsService=creatorsService;
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> getAll(){
        return creatorsService.getAll();
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> get(@PathVariable("id") int id){
        return creatorsService.get(id);
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> create(@RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        return creatorsService.create(creatorDTO,bindingResult);
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> update(@PathVariable("id") int id,
                                                  @RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        return creatorsService.update(id,creatorDTO,bindingResult);
    }

    public ResponseEntity<GoodResponse> delete(int id) {
        return creatorsService.delete(id);
    }
}
