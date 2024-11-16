package ee.mihkel.veebipood.exception;

import lombok.extern.log4j.Log4j2;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

// funktsioonidele: @ExceptionHandler
@ControllerAdvice
@Log4j2
public class ExceptionCatcher {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> CustomException(ValidationException e) {
        log.error(e);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName(e.getMessage()); // throw new Exception(->> "Email cannot be empty");
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> CustomException(TransientPropertyValueException e) {
        log.error(e);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName("Kategooria lisamata!"); // throw new Exception(->> "Email cannot be empty");
        errorMessage.setDate(new Date());
        return ResponseEntity.badRequest().body(errorMessage);
    }

    // LÕPUS: Live-eelne ka kõik ülejäänud exceptionid peita
//    @ExceptionHandler
//    public ResponseEntity<ErrorMessage> CustomException(RuntimeException e) {
//        log.error(e);
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setName("Unexpected error");
//        errorMessage.setDate(new Date());
//        return ResponseEntity.badRequest().body(errorMessage);
//    }
}
