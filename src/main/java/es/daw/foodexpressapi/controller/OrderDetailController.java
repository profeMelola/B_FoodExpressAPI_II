package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.order.OrderDetailDTO;
import es.daw.foodexpressapi.dto.order.OrderDetailViewDTO;
import es.daw.foodexpressapi.service.OrderDetailService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/order-details")
@RequiredArgsConstructor
public class OrderDetailController {
        private final OrderDetailService orderDetailService;

        @GetMapping
        public ResponseEntity<List<OrderDetailDTO>> findAll(){
            return ResponseEntity.ok(orderDetailService.findAll());
        }

        @GetMapping("/order/{orderId}/details")
        public ResponseEntity<List<OrderDetailViewDTO>> findViewByOrderId(@PathVariable Long orderId){
            return ResponseEntity.ok(orderDetailService.findViewByOrderId(orderId));
        }

        @GetMapping("/order/{orderId}/total")
        public ResponseEntity<BigDecimal> getTotal(@Min(2) @PathVariable Long orderId){
            return ResponseEntity.ok(orderDetailService.calculateTotal(orderId));
        }

}
