package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.*;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.enums.OrderStatus;
import es.daw.foodexpressapi.exception.InvalidStatusException;
import es.daw.foodexpressapi.exception.RestaurantNotFoundException;
import es.daw.foodexpressapi.exception.UserNotFoundException;
import es.daw.foodexpressapi.mapper.OrderMapper;
import es.daw.foodexpressapi.repository.OrderRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import es.daw.foodexpressapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponseDTO> filterOrders(String status, Long userId, Long restaurantId) {


        OrderStatus orderStatus = null;
        if (status != null) {
            if (!OrderStatus.isValid(status))
                throw new InvalidStatusException(status);
            orderStatus = OrderStatus.valueOf(status);
        }


        if (status != null && !OrderStatus.isValid(status)) {
            throw new InvalidStatusException(status);
        }

        if (userId != null && !userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        if (restaurantId != null && !restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantNotFoundException(restaurantId);
        }

//        List<Order> orders;
//
//        if (status != null && userId != null && restaurantId != null) {
//            orders = orderRepository.findByStatusAndUserIdAndRestaurantId(status, userId, restaurantId);
//
//        } else if (status != null && userId != null) {
//            orders = orderRepository.findByStatusAndUserId(status, userId);
//
//        } else if (status != null && restaurantId != null) {
//            orders = orderRepository.findByStatusAndRestaurantId(status, restaurantId);
//
//        } else if (userId != null && restaurantId != null) {
//            orders = orderRepository.findByUserIdAndRestaurantId(userId, restaurantId);
//
//        } else if (status != null) {
//            orders = orderRepository.findByStatus(status);
//
//        } else if (userId != null) {
//            orders = orderRepository.findByUserId(userId);
//
//        } else if (restaurantId != null) {
//            orders = orderRepository.findByRestaurantId(restaurantId);
//
//        } else {
//            orders = orderRepository.findAll();
//        }

        List<Order> orders = orderRepository.findByFilters(orderStatus, userId, restaurantId);

        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }

    public List<OrderSummaryDTO> getAllOrderSummaries() {
        return orderRepository.findAllOrderSummaries();
    }

    public List<CustomerTotalDTO> getAllCustomerTotals() {
        return orderRepository.findAllCustomerTotals();
    }

    public List<RestaurantOrderCountDTO> getAllRestaurantOrderCounts() {
        return orderRepository.findAllRestaurantOrderCounts();
    }

    public List<DishesOrderCountDTO> getAllDishesOrderCounts() {
        return orderRepository.findAllDishesOrderCounts();
    }


}
