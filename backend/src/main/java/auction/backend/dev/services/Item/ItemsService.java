package auction.backend.dev.services.Item;

import auction.backend.dev.base.EntityTag;
import auction.backend.dev.base.ResponseStatusTag;
import auction.backend.dev.dto.ItemDTO;
import auction.backend.dev.models.Item;
import auction.backend.dev.util.Excaption.common.ErrorInfo;
import auction.backend.dev.util.Excaption.common.NotCreatedException;
import auction.backend.dev.util.Excaption.common.NotUpdatedException;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
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
public class ItemsService {

    private final ItemsDBService itemsDBService;
    private final ModelMapper modelMapper;

    public ItemsService(ItemsDBService itemsDBService,
                        ModelMapper modelMapper){
        this.itemsDBService=itemsDBService;
        this.modelMapper=modelMapper;
    }

    public ResponseEntity<ResponseDTO<ItemDTO>>getAll(){
        List<Item>items=itemsDBService.getAll();
        List<ItemDTO>itemDTOS=new ArrayList<>();
        for(Item item : items)
            itemDTOS.add(convertToItemDTO(item));
        ResponseDTO<ItemDTO>response=new ResponseDTO<>(
                ResponseStatusTag.OK,
                EntityTag.ITEM,
                LocalDateTime.now(),
                itemDTOS
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseDTO<ItemDTO>>get(int id){
        ItemDTO item=convertToItemDTO(itemsDBService.get(id));
        ResponseDTO<ItemDTO> response=new ResponseDTO<>(
                ResponseStatusTag.OK,
                EntityTag.ITEM,
                LocalDateTime.now(),
                wrapItemDTO(item)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<ItemDTO>>create(ItemDTO itemDTO,
                                                      BindingResult bindingResult){
        validation("create",bindingResult);
        itemsDBService.create(convertToItem(itemDTO));
        ResponseDTO<ItemDTO>response=new ResponseDTO<>(
                ResponseStatusTag.OK,
                EntityTag.ITEM,
                LocalDateTime.now(),
                wrapItemDTO(itemDTO)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO<ItemDTO>>update(int id,
                                                      ItemDTO itemDTO,
                                                      BindingResult bindingResult){
        validation("update",bindingResult);
        itemsDBService.update(id,convertToItem(itemDTO));
        ResponseDTO<ItemDTO>response=new ResponseDTO<>(
                ResponseStatusTag.OK,
                EntityTag.ITEM,
                LocalDateTime.now(),
                wrapItemDTO(itemDTO)
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    public ResponseEntity<GoodResponse>delete(int id){
        itemsDBService.delete(id);
        GoodResponse response=new GoodResponse(
                "Item with ID "+id+" was deleted",
                HttpStatus.OK,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private void validation(String operation,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ErrorInfo>info=new ArrayList<>();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for (FieldError error : errors)
                info.add(new ErrorInfo(error.getField(),error.getDefaultMessage()));
            if(operation.equals("create"))
                throw new NotCreatedException(info);
            else throw new NotUpdatedException(info);
        }
    }

    private List<ItemDTO>wrapItemDTO(ItemDTO itemDTO){
        List<ItemDTO> entity=new ArrayList<>(1);
        entity.add(itemDTO);
        return entity;
    }

    private Item convertToItem(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, Item.class);
    }

    private ItemDTO convertToItemDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }

}
