package auction.backend.dev.controllers;

import auction.backend.dev.controllers.interfaces.ICreatorsController;
import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.models.Creator;
import auction.backend.dev.services.CreatorsService;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import auction.backend.dev.util.Excaption.Creator.CreatorNotCreatedException;
import auction.backend.dev.util.Excaption.Creator.CreatorNotUpdatedException;
import auction.backend.dev.util.Excaption.Creator.CreatorsExceptionHandler;
import auction.backend.dev.util.Validation.CreatorNameUniqueValidation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CreatorsExceptionHandler
@RequestMapping("api/v1.0/creators")
public class CreatorsController implements ICreatorsController {

    private final CreatorsService creatorsService;
    private final ModelMapper modelMapper;
    private final CreatorNameUniqueValidation creatorNameUniqueValidation;

    public CreatorsController(CreatorsService creatorsService,
                              ModelMapper modelMapper,
                              CreatorNameUniqueValidation creatorNameUniqueValidation){
        this.creatorsService=creatorsService;
        this.modelMapper=modelMapper;
        this.creatorNameUniqueValidation=creatorNameUniqueValidation;
    }

    public ResponseEntity<CreatorsCollectionResponse> getAllCreators(){
        List<Creator> creators=creatorsService.getAllCreators();
        List<CreatorDTO> creatorDTOS=new ArrayList<>();
        for(Creator creator : creators){
            creatorDTOS.add(convertToCreatorDTO(creator));
        }
        CreatorsCollectionResponse response=new CreatorsCollectionResponse(creatorDTOS.size(),
                LocalDateTime.now(),
                HttpStatus.OK,
                creatorDTOS);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<CreatorResponse> getCreator(@PathVariable("id") int id){
        CreatorDTO creator=convertToCreatorDTO(creatorsService.getCreatorById(id));
        CreatorResponse response=new CreatorResponse(
                HttpStatus.OK,
                LocalDateTime.now(),
                creator
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<CreatorResponse> createCreator(@RequestBody @Valid CreatorDTO creatorDTO,
                                                 BindingResult bindingResult){
        creatorNameUniqueValidation.validate(creatorDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CreatorNotCreatedException(message.toString());
        }
        creatorsService.createCreator(convertToCreator(creatorDTO));
        CreatorResponse response=new CreatorResponse(
                HttpStatus.OK,
                LocalDateTime.now(),
                creatorDTO
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<CreatorResponse> updateCreator(@PathVariable("id") int id,
                                                         @RequestBody @Valid CreatorDTO creatorDTO,
                                                         BindingResult bindingResult){
        creatorNameUniqueValidation.validate(creatorDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new CreatorNotUpdatedException(message.toString());
        }
        creatorsService.updateCreator(id,convertToCreator(creatorDTO));
        CreatorResponse response=new CreatorResponse(
                HttpStatus.OK,
                LocalDateTime.now(),
                creatorDTO
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> delete(int id) {
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

    private Creator convertToCreator(CreatorDTO creatorDTO){
        return modelMapper.map(creatorDTO, Creator.class);
    }

    private CreatorDTO convertToCreatorDTO(Creator creator){
        return modelMapper.map(creator, CreatorDTO.class);
    }
}
