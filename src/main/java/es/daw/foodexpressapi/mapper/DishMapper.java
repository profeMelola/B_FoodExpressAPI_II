package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishRequestDTO;
import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RestaurantMapper restaurantMapper;

    public DishRequestDTO toDTO(Dish dish) {
        if (dish == null) return null;

        DishRequestDTO dto = new DishRequestDTO();
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        //dto.setCategory(dish.getCategory());
        dto.setCategory(dish.getCategory() != null? dish.getCategory().getLabel() : "");

        dto.setRestaurantName(
                dish.getRestaurant() != null ? dish.getRestaurant().getName() : null
        );

        return dto;
    }

    // Pendiente!!! trabajar con optional para evitar nulos...
    public DishResponseDTO toResponseDTO(Dish dish) {
        if (dish == null) return null;

        BigDecimal base = dish.getPrice(); // precio de BD
        BigDecimal finalPrice = (dish.getCategory() != null)?
                    dish.getCategory().applyPlus(base): base;

        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(finalPrice)
                .basePrice(base)
                //.category(dish.getCategory())
                .category(dish.getCategory() != null? dish.getCategory().getLabel() : "")
                .restaurant(restaurantMapper.toDTO(dish.getRestaurant()))
                .build();
    }



}


