package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import auction.backend.dev.util.GoodResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ICreatorsController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "All creators are received"),
            @ApiResponse(responseCode = "404",description = "Not found creators"),
    })
    @GetMapping
    ResponseEntity<CreatorsCollectionResponse> getAll();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator is received")
    })
    @GetMapping("/{id}")
    ResponseEntity<CreatorResponse> get(@PathVariable("id") int id);

    @PostMapping
    ResponseEntity<CreatorResponse> create(@RequestBody @Valid CreatorDTO creatorDTO,
                                           BindingResult bindingResult);

    @PatchMapping("/{id}")
    ResponseEntity<CreatorResponse> update(@PathVariable("id") int id,
                                           @RequestBody @Valid CreatorDTO creatorDTO,
                                           BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<GoodResponse> delete(@PathVariable("id") int id);
}
