package auction.backend.dev.util;

import auction.backend.dev.dto.CreatorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CreatorsCollectionResponse {
    private int quantity;
    private LocalDateTime time;
    private HttpStatus status;
    private List<CreatorDTO> creators;
}
