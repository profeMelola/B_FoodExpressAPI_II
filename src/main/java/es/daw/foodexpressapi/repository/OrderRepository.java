package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.CustomerTotalDTO;
import es.daw.foodexpressapi.dto.DishesOrderCountDTO;
import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.dto.RestaurantOrderCountDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(String status);

    List<Order> findByUserId(Long userId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByStatusAndUserId(String status, Long userId);

    List<Order> findByStatusAndRestaurantId(String status, Long restaurantId);

    List<Order> findByUserIdAndRestaurantId(Long userId, Long restaurantId);

    List<Order> findByStatusAndUserIdAndRestaurantId(String status, Long userId, Long restaurantId);

    Optional<Order> findById(Long id);

    @Query("""
        SELECT o FROM Order o
            WHERE (:status IS NULL OR o.status =:status)
                AND (:userId IS NULL OR o.user.id =: userId)
                    AND (:restaurantId IS NULL OR o.restaurant.id =: restaurantId)
        
    """
    )
    public List<Order> findByFilters(
            //String status,
            OrderStatus status,
            Long userId,
            Long restaurantId
    );

    // ------------- PENDIENTE !!!!

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
    public List<OrderSummaryDTO> findAllOrderSummaries();

    @Query("""
            SELECT new es.daw.foodexpressapi.dto.CustomerTotalDTO(
                u.username,
                SUM(od.subtotal)
            )
            FROM Order o
            JOIN o.user u
            JOIN o.orderDetails od
            GROUP BY u.id
    """)
    public List<CustomerTotalDTO> findAllCustomerTotals();

    @Query("""
            SELECT new es.daw.foodexpressapi.dto.RestaurantOrderCountDTO(
                r.id,
                r.name,
                COUNT(o.id)
            )
            FROM Order o
            JOIN o.restaurant r
            GROUP BY r.id
            ORDER BY COUNT (o.id) DESC
    """)
    public List<RestaurantOrderCountDTO> findAllRestaurantOrderCounts();

    @Query("""
            SELECT new es.daw.foodexpressapi.dto.DishesOrderCountDTO(
                
                d.name,
                SUM(od.quantity)
            )
            FROM Order o
            JOIN o.orderDetails od
            JOIN od.dish d
            GROUP BY d.name
            ORDER BY SUM(od.quantity) DESC
    """)
    public List<DishesOrderCountDTO> findAllDishesOrderCounts();
}
