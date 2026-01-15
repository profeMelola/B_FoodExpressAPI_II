package es.daw.foodexpressapi.dto.report;

public record DishUnitsSoldDTO(
        Long dishId,
        String dishName,
        Long totalUnits
) {}
