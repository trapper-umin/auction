package auction.backend.dev.dto;

import auction.backend.dev.dto.common.AbstractDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonDTO extends AbstractDTO {

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3,max=255, message = "Size username should be between 3 and 255")
    private String name;

}
