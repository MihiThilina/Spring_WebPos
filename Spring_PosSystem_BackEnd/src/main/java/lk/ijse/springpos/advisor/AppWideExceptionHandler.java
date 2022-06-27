package lk.ijse.springpos.advisor;


import lk.ijse.springpos.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/

@CrossOrigin
@RestControllerAdvice
public class AppWideExceptionHandler {
//   apita oni stetus code ekak yawannna puluwan

//    @ExceptionHandler({Exception.class})
//    public ResponseEntity exceptionHandler(Exception e) {
//        //return new ResponseUtil(500, e.getMessage(), null);
//        return new ResponseEntity(new ResponseUtil(500, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler({Exception.class})
        public ResponseUtil exceptionHandler(Exception e) {
            return new ResponseUtil(500, e.getMessage(), null);
    }
}
