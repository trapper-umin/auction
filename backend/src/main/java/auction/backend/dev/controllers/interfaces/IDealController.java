package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.DealDTO;
import auction.backend.dev.util.Response.SuccessDealResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface IDealController {

    @Operation(summary = "Make a deal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Deal success"),
            @ApiResponse(responseCode = "400",description = "Bad request")
        }
    )
    @PostMapping
    ResponseEntity<SuccessDealResponse> create(
            @RequestBody @Valid DealDTO dealDTO,
            BindingResult bindingResult);
}
