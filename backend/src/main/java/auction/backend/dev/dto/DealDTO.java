package auction.backend.dev.dto;

import auction.backend.dev.dto.common.AbstractDTO;
import auction.backend.dev.models.Item;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO extends AbstractDTO {

    @Min(value = 0, message = "Final cost should be greater than zero(0)")
    private int finalCost;
}
