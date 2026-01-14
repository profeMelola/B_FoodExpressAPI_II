package es.daw.foodexpressapi.dto.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderDTO(
        @NotNull Long userId,
        @NotNull Long restaurantId,
        @NotEmpty @Valid List<OrderItemDTO> items
) {}
