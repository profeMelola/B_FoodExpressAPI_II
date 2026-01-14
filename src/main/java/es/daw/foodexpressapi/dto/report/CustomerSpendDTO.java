package es.daw.foodexpressapi.dto.report;

import java.math.BigDecimal;

public record CustomerSpendDTO(
        Long userId,
        String username,
        BigDecimal totalSpend
) {}

