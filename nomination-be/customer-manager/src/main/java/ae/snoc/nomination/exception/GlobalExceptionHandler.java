package ae.snoc.nomination.exception;

import ae.snoc.nomination.payload.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    ResponseEntity<ResponsePayload> noHandlerFoundExceptionHandler(NoHandlerFoundException nfe) {
        return new ResponseEntity<>(ResponsePayload.error(null, "Runtime error", HttpStatus.NOT_FOUND, "Please Check the URI"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ResponsePayload> customerNotFoundExceptionHandler(CustomerNotFoundException e) {
        return new ResponseEntity<>(ResponsePayload.error(null, e.getMessage(), HttpStatus.NOT_FOUND, "Failure"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponsePayload> genericExceptionHandler(Exception e) {
        return new ResponseEntity<>(ResponsePayload.error(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "Unknown Failure"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
