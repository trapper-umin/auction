package auction.backend.dev.controllers.abstracts;

import auction.backend.dev.controllers.interfaces.IDealController;
import auction.backend.dev.dto.DealDTO;
import auction.backend.dev.services.deal.DealService;
import auction.backend.dev.util.Response.SuccessDealResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@Tag(name = "Deal",description = "Allows you to make a deal")
public abstract class ImplDealControllerAbstract implements IDealController {

    private final DealService dealService;

    public ImplDealControllerAbstract(DealService dealService){
        this.dealService=dealService;
    }


    @Override
    public ResponseEntity<SuccessDealResponse> create(DealDTO dealDTO,
                                                      BindingResult bindingResult) {
        return dealService.create(dealDTO,bindingResult);
    }
}
