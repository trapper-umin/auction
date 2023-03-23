package auction.backend.dev.util;

import auction.backend.dev.util.Excaption.common.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BadResponseWithErrorsList{

    private HttpStatus status;
    private LocalDateTime time;
    private List<ErrorMessage> errors;
}
