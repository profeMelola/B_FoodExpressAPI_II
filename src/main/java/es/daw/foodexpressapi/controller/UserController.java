package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.OrderResponseDTO;

import es.daw.foodexpressapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final OrderService orderService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/orders")

    public ResponseEntity<List<OrderResponseDTO>> filterByUser(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.filterOrdersByUser(id));

    }


}
