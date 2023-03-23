package auction.backend.dev.util.Excaption.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDefault {

    private String error;

    public ErrorMessageDefault(){}
}
