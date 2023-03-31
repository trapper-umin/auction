package auction.backend.dev.util.Response;

import auction.backend.dev.base.EntityTag;
import auction.backend.dev.base.ResponseStatusTag;
import auction.backend.dev.dto.common.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO<E extends AbstractDTO> {

    private ResponseStatusTag status;

    private EntityTag tag;

    private LocalDateTime time;

    private List<E> entities;
}