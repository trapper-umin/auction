package auction.backend.dev.util.Excaption.Deal;

import auction.backend.dev.util.Excaption.common.NotValidException;
import auction.backend.dev.util.Response.BadResponseWithErrorsList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(annotations = DealsExceptionHandler.class)
public class DealsControllerAdvice {

    @ExceptionHandler(NotValidException.class)
    private ResponseEntity<BadResponseWithErrorsList> handleException(NotValidException e){
        BadResponseWithErrorsList response=new BadResponseWithErrorsList(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                e.getErrors()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
