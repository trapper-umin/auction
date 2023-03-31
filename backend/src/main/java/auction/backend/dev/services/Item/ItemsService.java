package auction.backend.dev.services.Item;

import auction.backend.dev.base.EntityTag;
import auction.backend.dev.dto.ItemDTO;
import auction.backend.dev.models.Item;
import auction.backend.dev.util.Response.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
                HttpStatus.OK,
                EntityTag.ITEM,
                LocalDateTime.now(),
                itemDTOS
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    private Item convertToItem(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, Item.class);
    }

    private ItemDTO convertToItemDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }

}
