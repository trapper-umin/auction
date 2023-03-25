package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import auction.backend.dev.util.GoodResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ICreatorsController {

    @Operation(summary = "Get all creators")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "All creators are received"),
            @ApiResponse(responseCode = "404",description = "Not found creators"),
    })
    @GetMapping
    ResponseEntity<CreatorsCollectionResponse> getAll();

    @Operation(summary = "Get creator by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator is received"),
            @ApiResponse(responseCode = "404",description = "Creator not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<CreatorResponse> get(
            @PathVariable("id") @Parameter(description = "Creator ID") int id);

    @Operation(summary = "Create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was created"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PostMapping
    ResponseEntity<CreatorResponse> create(@RequestBody @Valid CreatorDTO creatorDTO,
                                           BindingResult bindingResult);

    @Operation(summary = "Update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was updated"),
            @ApiResponse(responseCode = "404",description = "Creator not found"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PatchMapping("/{id}")
    ResponseEntity<CreatorResponse> update(
            @PathVariable("id") @Parameter(description = "Creator ID") int id,
            @RequestBody @Valid CreatorDTO creatorDTO,
            BindingResult bindingResult);

    @Operation(summary = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was deleted"),
            @ApiResponse(responseCode = "404",description = "Creator not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<GoodResponse> delete(
            @PathVariable("id") @Parameter(description = "Creator ID") int id);
}
