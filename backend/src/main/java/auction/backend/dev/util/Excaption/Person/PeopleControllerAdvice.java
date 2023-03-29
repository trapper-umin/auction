package auction.backend.dev.util.Excaption.Person;

import auction.backend.dev.util.Excaption.common.NotCreatedException;
import auction.backend.dev.util.Excaption.common.NotFoundException;
import auction.backend.dev.util.Excaption.common.NotUpdatedException;
import auction.backend.dev.util.Response.BadResponse;
import auction.backend.dev.util.Response.BadResponseWithErrorsList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(annotations = PeopleExceptionHandler.class)
public class PeopleControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<BadResponse>handleException(NotFoundException e){
        BadResponse response=new BadResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotCreatedException.class)
    private ResponseEntity<BadResponseWithErrorsList>handleException(NotCreatedException e){
        BadResponseWithErrorsList response=new BadResponseWithErrorsList(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                e.getErrors()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotUpdatedException.class)
    private ResponseEntity<BadResponseWithErrorsList>handleException(NotUpdatedException e){
        BadResponseWithErrorsList response=new BadResponseWithErrorsList(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                e.getErrors()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
