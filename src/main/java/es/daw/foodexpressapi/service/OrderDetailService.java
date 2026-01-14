package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.order.OrderDetailDTO;
import es.daw.foodexpressapi.dto.order.OrderDetailViewDTO;
import es.daw.foodexpressapi.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public List<OrderDetailDTO> findAll(){
        return orderDetailRepository.findAll()
                .stream()
                // MEJORA!!! implementar un sercicio de mapeo
                .map(od ->
                        new OrderDetailDTO(
                                od.getOrderDetailId().getOrderId(),
                                od.getOrderDetailId().getDishId(),
                                od.getQuantity(),
                                od.getSubtotal()
                        ))
                .toList();
    }

    public List<OrderDetailViewDTO> findViewByOrderId(Long orderId) {
        //Mejoras... si no hay pedido!!!! excepción???? Nada de optional al controlador!!!
        return orderDetailRepository.findViewByOrderId(orderId)
                .orElseThrow( () ->new RuntimeException("No hay detalles del pedido... "));
        //return orderDetailRepository.findViewByOrderId(orderId);
    }

    public BigDecimal calculateTotal(Long orderId) {

        return orderDetailRepository.calculateTotal(orderId);
    }


}
