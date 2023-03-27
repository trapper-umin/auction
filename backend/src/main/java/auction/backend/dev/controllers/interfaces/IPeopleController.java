package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.PersonDTO;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface IPeopleController {

    @Operation(summary = "Get all people")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "All people were received"),
            @ApiResponse(responseCode = "404",description = "Not found people in DB")
    })
    @GetMapping
    ResponseEntity<ResponseDTO<PersonDTO>> getAll();

    @Operation(summary = "Get person by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Person was received by ID"),
            @ApiResponse(responseCode = "404",description = "Not found person with this ID")
    })
    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO<PersonDTO>> get(
            @Parameter(description = "Person ID") @PathVariable("id") int id);

    @Operation(summary = "Create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Person was created"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PostMapping
    ResponseEntity<ResponseDTO<PersonDTO>> create(
            @RequestBody @Valid PersonDTO personDTO,
            BindingResult bindingResult);

    @Operation(summary = "Update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Person was updated"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)"),
            @ApiResponse(responseCode = "404",description = "Not fount person with this ID")
    })
    @PatchMapping("/{id}")
    ResponseEntity<ResponseDTO<PersonDTO>> update(
            @PathVariable("id") @Parameter(description = "Person ID") int id,
            @RequestBody @Valid PersonDTO personDTO,
            BindingResult bindingResult);

    @Operation(summary = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Person was deleted"),
            @ApiResponse(responseCode = "404",description = "Not found person with this ID")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<GoodResponse> delete(
            @PathVariable("id") @Parameter(description = "Person ID") int id);
}
