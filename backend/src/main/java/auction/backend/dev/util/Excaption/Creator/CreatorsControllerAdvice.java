package auction.backend.dev.util.Excaption.Creator;

import auction.backend.dev.util.BadResponse;
import auction.backend.dev.util.BadResponseWithErrorsList;
import auction.backend.dev.util.Excaption.common.NotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(annotations = CreatorsExceptionHandler.class)
public class CreatorsControllerAdvice {

    @ExceptionHandler
    private ResponseEntity<BadResponse> handleException(CreatorsNotFoundException e){
        BadResponse response=new BadResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BadResponse> handleException(CreatorNotFoundException e){
        BadResponse response=new BadResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BadResponse> handleException(CreatorNotUpdatedException e){
        BadResponse response=new BadResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotCreatedException.class)
    private ResponseEntity<BadResponseWithErrorsList> handleException(NotCreatedException e){
        BadResponseWithErrorsList response=new BadResponseWithErrorsList(
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now(),
                e.getErrors()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
