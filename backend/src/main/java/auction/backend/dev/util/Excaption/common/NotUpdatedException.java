package auction.backend.dev.util.Excaption.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotUpdatedException extends RuntimeException{

    private List<ErrorMessage> errors;
}
