package es.daw.foodexpressapi.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemDTO(
        @NotNull Long dishId,
        @NotNull @Min(1) Integer quantity
) {}
