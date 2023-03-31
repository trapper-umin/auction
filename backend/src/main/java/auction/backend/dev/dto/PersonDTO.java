package auction.backend.dev.dto;

import auction.backend.dev.dto.common.AbstractDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO extends AbstractDTO {

    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3,max=255, message = "Size username should be between 3 and 255")
    private String name;

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 10,max = 50,message = "Password size should be between 10 and 50")
    private String password;

    @NotNull(message = "Cash should be not empty")
    @Min(value = 0,message = "Minimal size of sash is 0")
    private int cash;
}
