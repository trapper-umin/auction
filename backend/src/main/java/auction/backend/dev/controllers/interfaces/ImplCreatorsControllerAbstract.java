package auction.backend.dev.controllers.interfaces;

import auction.backend.dev.dto.CreatorDTO;
import auction.backend.dev.util.CreatorResponse;
import auction.backend.dev.util.CreatorsCollectionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class ImplCreatorsControllerAbstract implements ICreatorsController{
    @Override
    public ResponseEntity<CreatorsCollectionResponse> getAllCreators() {
        return null;
    }

    @Override
    public ResponseEntity<CreatorResponse> getCreator(int id) {
        return null;
    }

    @Override
    public ResponseEntity<CreatorResponse> createCreator(CreatorDTO creatorDTO, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<CreatorResponse> updateCreator(int id, CreatorDTO creatorDTO, BindingResult bindingResult) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> delete(int id) {
        return null;
    }
}
