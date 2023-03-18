package auction.backend.dev.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3,max=255, message = "Size username should be between 3 and 255")
    private String name;
}
