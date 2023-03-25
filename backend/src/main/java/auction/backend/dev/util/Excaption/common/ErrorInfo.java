package auction.backend.dev.util.Excaption.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorInfo {

    private String field;
    private String error;
}
