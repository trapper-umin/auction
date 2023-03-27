package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.CreatorDTO;
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

public interface ICreatorsController {

    @Operation(summary = "Get all creators")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "All creators were received"),
            @ApiResponse(responseCode = "404",description = "Not found creators"),
    })
    @GetMapping
    ResponseEntity<ResponseDTO<CreatorDTO>> getAll();

    @Operation(summary = "Get creator by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was received"),
            @ApiResponse(responseCode = "404",description = "Creator not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO<CreatorDTO>> get(
            @PathVariable("id") @Parameter(description = "Creator ID") int id);

    @Operation(summary = "Create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was created"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PostMapping
    ResponseEntity<ResponseDTO<CreatorDTO>> create(@RequestBody @Valid CreatorDTO creatorDTO,
                                           BindingResult bindingResult);

    @Operation(summary = "Update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Creator was updated"),
            @ApiResponse(responseCode = "404",description = "Creator not found"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PatchMapping("/{id}")
    ResponseEntity<ResponseDTO<CreatorDTO>> update(
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
