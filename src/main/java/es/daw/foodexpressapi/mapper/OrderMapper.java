package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponseDTO toResponse(Order o) {
        return OrderResponseDTO.builder()
                .id(o.getId())
                .orderDate(o.getOrderDate())
                //.status(o.getStatus())
                .status(o.getStatus().name())
                .userId(o.getUser().getId())
                .username(o.getUser().getUsername())
                .restaurantId(o.getRestaurant().getId())
                .restaurantName(o.getRestaurant().getName())
                .build();
    }
}
