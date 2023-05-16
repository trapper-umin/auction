package auction.backend.dev.services.deal;

import auction.backend.dev.base.ResponseStatusTag;
import auction.backend.dev.dto.DealDTO;
import auction.backend.dev.dto.PersonDTOResponse;
import auction.backend.dev.models.Person;
import auction.backend.dev.util.Excaption.common.ErrorInfo;
import auction.backend.dev.util.Excaption.common.NotValidException;
import auction.backend.dev.util.Response.SuccessDealResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                                                      BindingResult bindingResult){

        List<Person>people = dealDBService.create(dealDTO);

        SuccessDealResponse response=new SuccessDealResponse(
                ResponseStatusTag.SUCCESS,
                LocalDateTime.now(),
                convertToPersonDTOResponse(people.get(0)),
                convertToPersonDTOResponse(people.get(1))
                
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validation(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ErrorInfo> info=new ArrayList<>();
            List<FieldError>errors=bindingResult.getFieldErrors();
            for(FieldError error : errors)
                info.add(new ErrorInfo(error.getField(),error.getDefaultMessage()));
            throw new NotValidException(info);
        }
    }

    private PersonDTOResponse convertToPersonDTOResponse(Person person){
        return modelMapper.map(person,PersonDTOResponse.class);
    }
}
