package auction.backend.dev.controllers;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.models.Creator;
import auction.backend.dev.services.CreatorsService;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import auction.backend.dev.util.Excaption.Creator.CreatorNotCreatedException;
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
@RequestMapping("api/v1.0/creators") //будет ли работать с / перед api ? (Мутод проб и ошибок)
public class CreatorsController {

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

    @GetMapping
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

    @GetMapping("/{id}")
    public ResponseEntity<CreatorResponse> getCreator(@PathVariable("id") int id){
        CreatorDTO creator=convertToCreatorDTO(creatorsService.getCreatorById(id));
        CreatorResponse response=new CreatorResponse(
                HttpStatus.OK,
                LocalDateTime.now(),
                creator
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatorResponse> createCreator(@RequestBody @Valid CreatorDTO creatorDTO,
                                                 BindingResult bindingResult){
        creatorNameUniqueValidation.validate(creatorDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField())
                        .append("-")
                        .append(error.getRejectedValue())
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

    private Creator convertToCreator(CreatorDTO creatorDTO){
        return modelMapper.map(creatorDTO, Creator.class);
    }

    private CreatorDTO convertToCreatorDTO(Creator creator){
        return modelMapper.map(creator, CreatorDTO.class);
    }
}
