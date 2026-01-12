package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record CustomerTotalDTO(
        String username,
        BigDecimal totalAmount
) {}
