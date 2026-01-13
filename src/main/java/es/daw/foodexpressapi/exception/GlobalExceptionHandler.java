package es.daw.foodexpressapi.exception;

import es.daw.foodexpressapi.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.parser.HttpHeaderParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(InvalidStatusException.class)
//    public ResponseEntity<String> handleInvalidStatus(InvalidStatusException ex) {
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(RestaurantNotFoundException.class)
//    public ResponseEntity<String> handleRestaurantNotFound(RestaurantNotFoundException ex) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    }
    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorDTO> handleInvalidStatus(InvalidStatusException ex,  HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleRestaurantNotFound(RestaurantNotFoundException ex,  HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    /**
     *     private LocalDateTime timestamp; // Momento del error
     *
     *     private int status;              // Código HTTP (404, 400, 500...)
     *     private String error;            // Nombre del error: "Not Found", "Bad Request"...
     *     private String message;          // Mensaje detallado
     *     private String path;             // Endpoint que falló (/api/dishes, etc.)
     * @return
     */
    private ResponseEntity<ErrorDTO> buildError(HttpStatus status, String message, HttpServletRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(status.value()); // Código HTTP (404, 400, 500...)
        errorDTO.setError(status.getReasonPhrase()); // Nombre del error: "Not Found", "Bad Request"...
        errorDTO.setMessage(message);
        errorDTO.setTimestamp(LocalDateTime.now()); // no formateamos... lo dejamos en dto
        errorDTO.setPath(request.getRequestURI()); // Endpoint que falló (/api/dishes, etc.)
        return ResponseEntity.status(status).body(errorDTO);

    }

}
