package auction.backend.dev.services;

import auction.backend.dev.base.ResponseStatusTag;
import auction.backend.dev.dto.DealDTO;
import auction.backend.dev.dto.PersonDTOResponse;
import auction.backend.dev.models.Person;
import auction.backend.dev.util.Response.SuccessDealResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealService {

    private final ModelMapper modelMapper;
    private final DealDBService dealDBService;

    public DealService(ModelMapper modelMapper,
                       DealDBService dealDBService){
        this.modelMapper=modelMapper;
        this.dealDBService = dealDBService;
    }

    public ResponseEntity<SuccessDealResponse> create(DealDTO dealDTO,
                                                      BindingResult bindingResult,
                                                      int sellerId,
                                                      int buyerId,
                                                      int itemId){
        //TODO validation

        List<Person>people= dealDBService.create(dealDTO,sellerId,buyerId,itemId);

        SuccessDealResponse response=new SuccessDealResponse(
                ResponseStatusTag.SUCCESS,
                LocalDateTime.now(),
                convertToPersonDTOResponse(people.get(0)),
                convertToPersonDTOResponse(people.get(1))
                
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PersonDTOResponse convertToPersonDTOResponse(Person person){
        return modelMapper.map(person,PersonDTOResponse.class);
    }
}
