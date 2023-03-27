package auction.backend.dev.services;

import auction.backend.dev.base.EntityTag;
import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.models.Creator;
import auction.backend.dev.util.Excaption.common.ErrorInfo;
import auction.backend.dev.util.Excaption.common.NotCreatedException;
import auction.backend.dev.util.Excaption.common.NotUpdatedException;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import auction.backend.dev.util.Validation.CreatorNameUniqueValidation;
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
public class CreatorsService {

    private final ModelMapper modelMapper;
    private final CreatorNameUniqueValidation creatorNameUniqueValidation;
    private final CreatorsDBService creatorsDBService;

    public CreatorsService(ModelMapper modelMapper,
                           CreatorNameUniqueValidation creatorNameUniqueValidation,
                           CreatorsDBService creatorsDBService){

        this.modelMapper = modelMapper;
        this.creatorNameUniqueValidation = creatorNameUniqueValidation;
        this.creatorsDBService = creatorsDBService;
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> getAll(){
        List<Creator> creators= creatorsDBService.getAll();
        List<CreatorDTO> creatorDTOS=new ArrayList<>();
        for(Creator creator : creators){
            creatorDTOS.add(convertToCreatorDTO(creator));
        }

        ResponseDTO<CreatorDTO> response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.CREATOR,
                LocalDateTime.now(),
                creatorDTOS
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> get(int id){
        CreatorDTO creator=convertToCreatorDTO(creatorsDBService.get(id));

        ResponseDTO<CreatorDTO> response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.CREATOR,
                LocalDateTime.now(),
                wrapCreatorDTO(creator)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> create(CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        creatorNameUniqueValidation.validate(creatorDTO,bindingResult);
        if(bindingResult.hasErrors())
           detectErrors("create",bindingResult);

        creatorsDBService.create(convertToCreator(creatorDTO));

        ResponseDTO<CreatorDTO> response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.CREATOR,
                LocalDateTime.now(),
                wrapCreatorDTO(creatorDTO)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<CreatorDTO>> update(int id,
                                                  CreatorDTO creatorDTO,
                                                  BindingResult bindingResult){
        creatorNameUniqueValidation.validate(creatorDTO,bindingResult);
        if(bindingResult.hasErrors())
            detectErrors("update",bindingResult);
        creatorsDBService.update(id,convertToCreator(creatorDTO));

        ResponseDTO<CreatorDTO> response=new ResponseDTO<>(
                HttpStatus.OK,
                EntityTag.CREATOR,
                LocalDateTime.now(),
                wrapCreatorDTO(creatorDTO)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<GoodResponse> delete(int id){
        creatorsDBService.delete(id);
        GoodResponse response=new GoodResponse(
                "Creator with id "+id+" was deleted",
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private Creator convertToCreator(CreatorDTO creatorDTO){
        return modelMapper.map(creatorDTO, Creator.class);
    }

    private CreatorDTO convertToCreatorDTO(Creator creator){
        return modelMapper.map(creator, CreatorDTO.class);
    }

    private List<CreatorDTO> wrapCreatorDTO(CreatorDTO creatorDTO){
        List<CreatorDTO>entity=new ArrayList<>();
        entity.add(creatorDTO);
        return entity;
    }

    private void detectErrors(String operation,
                              BindingResult bindingResult){
        List<ErrorInfo> info=new ArrayList<>();
        List<FieldError> errors=bindingResult.getFieldErrors();
        for(FieldError error : errors)
            info.add(new ErrorInfo(error.getField(),error.getDefaultMessage()));
        if(operation.equals("create"))
            throw new NotCreatedException(info);
        else throw new NotUpdatedException(info);
    }
}
