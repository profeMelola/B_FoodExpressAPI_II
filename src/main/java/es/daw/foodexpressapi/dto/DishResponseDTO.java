package es.daw.foodexpressapi.dto;


import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DishResponseDTO {

    private Long id;
    private String name;

    private BigDecimal price; //precio final (plus)
    private BigDecimal basePrice; // precio guardado

    private String category;

    @Valid
    private RestaurantResponseDTO restaurant;
}

