package auction.backend.dev.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GoodResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime time;
}
