package auction.backend.dev.dto;

import auction.backend.dev.dto.common.AbstractDTO;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class CreatorDTO extends AbstractDTO{

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 3,max = 255,message = "Name should be between 3 and 255")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    @Size(min=3,max = 255,message = "Description should be between 3 and 255")
    private String description;
}
