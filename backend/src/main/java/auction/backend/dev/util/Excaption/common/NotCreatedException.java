package auction.backend.dev.util.Excaption.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
public class NotCreatedException extends RuntimeException{

    List<ErrorMessage> errors;
    public NotCreatedException(List<ErrorMessage> errors){
        this.errors=errors;
    }
}
