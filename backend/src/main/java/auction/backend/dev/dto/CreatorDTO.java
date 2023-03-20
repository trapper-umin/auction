package auction.backend.dev.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatorDTO {

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 3,max = 255,message = "Name should be between 3 and 255")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    @Size(min=3,max = 255,message = "Description should be between 3 and 255")
    private String description;
}
