package ae.snoc.nomination.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePayload {

    private Object data;
    private Object error;
    private HttpStatus httpStatus;
    private LocalDateTime timeStamp;
    private String message;

    private ResponsePayload(Object data, Object error, HttpStatus httpStatus, String message){
        this.data = data;
        this.error = error;
        this.httpStatus = httpStatus;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
    }
    public static ResponsePayload create(Object data, Object error, HttpStatus httpStatus, String message ){
        return new ResponsePayload(data, null, httpStatus, message);
    }

    public static ResponsePayload error(Object data, Object error, HttpStatus httpStatus, String message ){
        return new ResponsePayload(null, error, httpStatus, message);
    }
}
