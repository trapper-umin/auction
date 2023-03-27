package auction.backend.dev.util.Excaption.common;

import lombok.Data;

import java.util.List;
@Data
public class NotCreatedException extends RuntimeException{

    List<ErrorInfo> errors;
    public NotCreatedException(List<ErrorInfo> errors){
        this.errors=errors;
    }
}
