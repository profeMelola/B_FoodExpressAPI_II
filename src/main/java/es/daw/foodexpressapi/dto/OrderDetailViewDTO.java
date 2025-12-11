package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record OrderDetailViewDTO(
        String dishName,
        String category, //pendiente mejora!!! tipo enumerado
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
