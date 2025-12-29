package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.*;
import es.daw.foodexpressapi.service.OrderService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
//@Validated //?????????????
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> filterOrders(
            @RequestParam(required = false) String status,
            @Min(1) @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long restaurantId
    ) {

            return ResponseEntity.ok(orderService.filterOrders(status, userId, restaurantId));

    }

    @GetMapping("/summary")
    public ResponseEntity<List<OrderSummaryDTO>> getOrderSummaries() {
        return ResponseEntity.ok(orderService.getAllOrderSummaries());
    }

    @GetMapping("/totals-by-customer")
    public ResponseEntity<List<CustomerTotalDTO>> getCustomerTotalsByCustomer(){
        return ResponseEntity.ok(orderService.getAllCustomerTotals());
    }

    @GetMapping("/best-restaurants")
    public ResponseEntity<List<RestaurantOrderCountDTO>> getBestRestaurant(){
        return ResponseEntity.ok(orderService.getAllRestaurantOrderCounts());
    }

    @GetMapping("/best-dishes")
    public ResponseEntity<List<DishesOrderCountDTO>> getBestDishes(){
        return ResponseEntity.ok(orderService.getAllDishesOrderCounts());
    }
}
