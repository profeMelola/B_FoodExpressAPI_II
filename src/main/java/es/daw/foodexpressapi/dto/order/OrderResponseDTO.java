package es.daw.foodexpressapi.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime orderDate;
    private String status;

    private Long userId;
    private String username;

    private Long restaurantId;
    private String restaurantName;
}