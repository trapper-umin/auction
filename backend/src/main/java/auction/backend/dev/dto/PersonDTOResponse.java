package auction.backend.dev.dto;

import auction.backend.dev.dto.common.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDTOResponse extends AbstractDTO {

    private String name;

    private int cash;

    private List<ItemDTO> items;
}
