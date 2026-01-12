package es.daw.foodexpressapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishRequestDTO {

    private String name;

    private BigDecimal price;

    private String category;

    private String restaurantName;
}
