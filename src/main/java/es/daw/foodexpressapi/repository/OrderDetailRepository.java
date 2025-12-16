package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.OrderDetailViewDTO;
import es.daw.foodexpressapi.entity.OrderDetail;
import es.daw.foodexpressapi.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

    @Query("""
            SELECT new es.daw.foodexpressapi.dto.OrderDetailViewDTO(
                d.name,
                d.category,
                od.quantity,
                d.price,
                od.subtotal
                )
            FROM OrderDetail od
            JOIN od.dish d
            WHERE od.order.id = :orderId
    """)
    public Optional<List<OrderDetailViewDTO>> findViewByOrderId(Long orderId);
    //List<OrderDetailViewDTO> findViewByOrderId(Long orderId);

    @Query("""
        SELECT SUM(od.subtotal)
            FROM OrderDetail od
                WHERE od.order.id = :orderId
    """)
    public BigDecimal calculateTotal(Long orderId);


}
