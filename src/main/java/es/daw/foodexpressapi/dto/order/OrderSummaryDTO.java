package es.daw.foodexpressapi.dto.order;

import java.math.BigDecimal;

public record OrderSummaryDTO(
        Long orderId,
        String username,
        String restaurantName,
        Long totalItems,
        BigDecimal totalAmount
) {}
