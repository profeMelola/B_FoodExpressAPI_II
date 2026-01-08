package es.daw.foodexpressapi.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DishCategory {

    HAMBURGUESAS("Hamburguesas", new BigDecimal("0.10")),
    PASTA("Pasta", new BigDecimal("0.05")),
    SUSHI("Sushi", new BigDecimal("0.15")),
    ENTRANTE("Entrante", new BigDecimal("0.00")),
    POSTRE("Postre", new BigDecimal("0.03"));

    // Atributos adicionales...
    private final String label;
    private final BigDecimal plusRate; // Incremento de precio por porcentaje

    // Constructor privado
    DishCategory(String label,  BigDecimal plusRate) {
        this.label = label;
        this.plusRate = plusRate;
    }

    // Métodos auxiliares...
    public BigDecimal applyPlus(BigDecimal basePrice) {
        if (basePrice == null) return null;
        return basePrice.add(basePrice.multiply(plusRate));

    }

}
