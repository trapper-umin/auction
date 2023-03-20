package auction.backend.dev.util;

import auction.backend.dev.dto.CreatorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreatorResponse {

    private HttpStatus status;
    private LocalDateTime time;
    private CreatorDTO creator;
}
