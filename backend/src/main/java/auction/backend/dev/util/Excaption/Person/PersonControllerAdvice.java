package auction.backend.dev.util.Excaption.Person;

import auction.backend.dev.util.PeopleBadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class PersonControllerAdvice {

    @ExceptionHandler
    private ResponseEntity<PeopleBadResponse> handleException(PeopleNotFoundException peopleNotFoundException){
        PeopleBadResponse response=new PeopleBadResponse(
                "The database is currently empty",
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
