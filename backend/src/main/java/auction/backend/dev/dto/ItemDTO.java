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
public class ItemDTO extends AbstractDTO {

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 4,max = 255,message = "Name size should be between 4 and 255")
    private String name;

    @NotEmpty(message = "Description should be not empty")
    @Size(min = 4,max = 255,message = "Description size should be between 4 and 255")
    private String description;

    @NotNull(message = "Cost should be not null")
    @Min(value = 0,message = "Minimal size of cost is 0")
    private int cost;

    @NotNull(message = "Step should be not null")
    @Min(value = 5, message = "Minimal step is 5")
    private int step;

}
