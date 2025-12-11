package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(String status);

    List<Order> findByUserId(Long userId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByStatusAndUserId(String status, Long userId);

    List<Order> findByStatusAndRestaurantId(String status, Long restaurantId);

    List<Order> findByUserIdAndRestaurantId(Long userId, Long restaurantId);

    List<Order> findByStatusAndUserIdAndRestaurantId(String status, Long userId, Long restaurantId);

    @Query("""
        SELECT new es.daw.foodexpressapi.dto.OrderSummaryDTO(
            o.id,
            u.username,
            r.name,
            SUM(od.quantity),
            SUM(od.subtotal)
        )
        FROM Order o
        JOIN o.user u
        JOIN o.restaurant r
        JOIN o.orderDetails od
        GROUP BY o.id, u.username, r.name
        ORDER BY o.id
        """)
    List<OrderSummaryDTO> findAllOrderSummaries();



}
