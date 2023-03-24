package auction.backend.dev.controllers.abstracts;

import auction.backend.dev.controllers.interfaces.ICreatorsController;
import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.services.CreatorsService;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import auction.backend.dev.util.GoodResponse;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api
public abstract class ImplCreatorsControllerAbstract implements ICreatorsController {

    private final CreatorsService creatorsService;
    public ImplCreatorsControllerAbstract(CreatorsService creatorsService){
        this.creatorsService=creatorsService;
    }

    public ResponseEntity<CreatorsCollectionResponse> getAll(){
        return creatorsService.getAll();
    }

    public ResponseEntity<CreatorResponse> get(@PathVariable("id") int id){
        return creatorsService.get(id);
    }

    public ResponseEntity<CreatorResponse> create(@RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        return creatorsService.create(creatorDTO,bindingResult);
    }

    public ResponseEntity<CreatorResponse> update(@PathVariable("id") int id,
                                                  @RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        return creatorsService.update(id,creatorDTO,bindingResult);
    }

    public ResponseEntity<GoodResponse> delete(int id) {
        return creatorsService.delete(id);
    }
}
