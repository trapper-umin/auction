package auction.backend.dev.util.Excaption.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotValidException extends RuntimeException{

    private List<ErrorInfo> errors;
}
