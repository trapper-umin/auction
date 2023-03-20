package auction.backend.dev.util;

import auction.backend.dev.dto.PersonDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PeopleDTOResponse {

    private LocalDateTime time;
    private List<PersonDTO> persons;

    public PeopleDTOResponse(){}

    public PeopleDTOResponse(LocalDateTime time,List<PersonDTO> persons){
        this.time=time;
        this.persons=persons;
    }

    public List<PersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTO> persons) {
        this.persons = persons;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
