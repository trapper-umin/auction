package auction.backend.dev.util.Excaption.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage{

    private String field;
    private String error;
}
