package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ICreatorsController {

    @GetMapping
    ResponseEntity<CreatorsCollectionResponse> getAllCreators();

    @GetMapping("/{id}")
    ResponseEntity<CreatorResponse> getCreator(@PathVariable("id") int id);

    @PostMapping
    ResponseEntity<CreatorResponse> createCreator(@RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult);


    @PatchMapping("/{id}")
    ResponseEntity<CreatorResponse> updateCreator(@PathVariable("id") int id,
                                                  @RequestBody @Valid CreatorDTO creatorDTO,
                                                  BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") int id);
}
