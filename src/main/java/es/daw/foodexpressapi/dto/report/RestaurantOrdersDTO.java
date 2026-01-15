package es.daw.foodexpressapi.dto.report;

public record RestaurantOrdersDTO(
        Long restaurantId,
        String restaurantName,
        Long totalOrders
) {}
