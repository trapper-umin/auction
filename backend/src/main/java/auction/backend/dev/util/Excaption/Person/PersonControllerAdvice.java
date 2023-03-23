package auction.backend.dev.util.Excaption.Person;

import auction.backend.dev.util.BadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(annotations = PeopleExceptionHandler.class)
public class PersonControllerAdvice {

    @ExceptionHandler
    private ResponseEntity<BadResponse> handleException(PeopleNotFoundException peopleNotFoundException){
        BadResponse response=new BadResponse(
                "The database is currently empty",
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
