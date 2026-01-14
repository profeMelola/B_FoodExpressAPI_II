package es.daw.foodexpressapi.dto.order;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record OrderDetailViewDTO(
        @NotEmpty
        String dishName,
        //@Pattern()
        String category, //pendiente mejora!!! tipo enumerado
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
