package auction.backend.dev.controllers.abstracts;

import auction.backend.dev.controllers.interfaces.IItemsController;
import auction.backend.dev.dto.ItemDTO;
import auction.backend.dev.services.Item.ItemsService;
import auction.backend.dev.util.Response.GoodResponse;
import auction.backend.dev.util.Response.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@Tag(name = "Items",description = "A REST controller that allows you to commit with the items of this site")
public abstract class ImplItemsControllerAbstract implements IItemsController {

    private final ItemsService itemsService;

    public ImplItemsControllerAbstract(ItemsService itemsService){
        this.itemsService=itemsService;
    }

    @Override
    public ResponseEntity<ResponseDTO<ItemDTO>> getAll() {
        return itemsService.getAll();
    }

    @Override
    public ResponseEntity<ResponseDTO<ItemDTO>> get(int id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO<ItemDTO>> create(ItemDTO itemDTO, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO<ItemDTO>> update(int id, ItemDTO itemDTO, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<GoodResponse> delete(int id) {
        return null;
    }
}
