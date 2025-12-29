package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record RestaurantOrderCountDTO(
        Long orderId,
        String restaurantName,
        Long totalOrders

) {}
