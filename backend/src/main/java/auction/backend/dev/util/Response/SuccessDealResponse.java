package auction.backend.dev.util.Response;

import auction.backend.dev.base.ResponseStatusTag;
import auction.backend.dev.dto.PersonDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class SuccessDealResponse {

    private ResponseStatusTag status;

    private LocalDateTime time;

    private PersonDTOResponse seller;

    private PersonDTOResponse buyer;

}
