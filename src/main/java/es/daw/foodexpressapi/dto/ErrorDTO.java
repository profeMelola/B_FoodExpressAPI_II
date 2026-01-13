package es.daw.foodexpressapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp; // Momento del error

    private int status;              // Código HTTP (404, 400, 500...)
    private String error;            // Nombre del error: "Not Found", "Bad Request"...
    private String message;          // Mensaje detallado
    private String path;             // Endpoint que falló (/api/dishes, etc.)
}