package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.ItemDTO;
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

public interface IItemsController {

    @Operation(summary = "Get all items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "All items were received"),
            @ApiResponse(responseCode = "404",description = "Not found items in DB")
    })
    @GetMapping
    ResponseEntity<ResponseDTO<ItemDTO>> getAll();

    @Operation(summary = "Get item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Item was received by ID"),
            @ApiResponse(responseCode = "404", description = "Item with this ID not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO<ItemDTO>> get(
            @Parameter(description = "Item ID") @PathVariable("id")int id);

    @Operation(summary = "Create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Item was created"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)")
    })
    @PostMapping
    ResponseEntity<ResponseDTO<ItemDTO>> create(
            @RequestBody @Valid ItemDTO itemDTO,
            BindingResult bindingResult);

    @Operation(summary = "Update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Item was updated"),
            @ApiResponse(responseCode = "400",description = "Bad request (The entity failed validation)"),
            @ApiResponse(responseCode = "404",description = "Item with  this ID not found")
    })
    @PatchMapping("/{id}")
    ResponseEntity<ResponseDTO<ItemDTO>> update(
            @Parameter(description = "Item ID") @PathVariable("id")int id,
            @RequestBody @Valid ItemDTO itemDTO,
            BindingResult bindingResult);

    @Operation(summary = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Item was deleted"),
            @ApiResponse(responseCode = "404",description = "Item with this ID not found")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<GoodResponse> delete(
            @Parameter(description = "Item ID") @PathVariable("id")int id);
}
