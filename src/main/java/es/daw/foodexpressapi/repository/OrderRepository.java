package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.CustomerTotalDTO;
import es.daw.foodexpressapi.dto.DishesOrderCountDTO;
import es.daw.foodexpressapi.dto.order.OrderSummaryDTO;
import es.daw.foodexpressapi.dto.RestaurantOrderCountDTO;
import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO;
import es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.enums.OrderStatus;
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
        SELECT new es.daw.foodexpressapi.dto.order.OrderSummaryDTO(
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


    // ---------- TRES ENDPOINTS DEL GITHUB -----------------

    /*
    - Agrupar pedidos por usuario
    - Sumar el subtotal de todas las líneas de pedido (OrderDetail)
    - Devolver un ranking ordenado por gasto total

    - La entidad raíz es Order.
    - Cada fila de partida es un pedido.
    - Desde ahí navegamos a User y a OrderDetail
    - Order tiene una relación @OneToMany con OrderDetail
    - Cada pedido tiene varias líneas
    - Cada línea tiene un subtotal
    - Esto multiplica filas internamente (una por cada línea), lo cual es justo lo que necesitamos para poder sumar.

    - SUM() es una función agregada
    - Obliga al uso de GROUP BY

    - u.id → no agregado → debe ir en GROUP BY
    - u.username → no agregado → debe ir en GROUP BY
    - SUM(od.subtotal) → agregado → NO va en GROUP BY

    - Si no se pone nada, en JPQL es un INNER JOIN por defecto
    - Solo cuentan los pedidos que tengan líneas, es decir, que tengan detalles asociados. Para tenerlo en cuenta habría que cambiar a LEFT JOIN
    - Si quisiéramos incluir también pedidos sin líneas, habría que usar LEFT JOIN, y además tratar los valores null en la suma.


    - Se hace una proyección a DTO (no se devuelven entidades)

 */
    //¿Cuánto ha gastado cada cliente?
    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.CustomerSpendDTO(
            u.id,
            u.username,
            SUM(od.subtotal)
        )
        FROM Order o
        JOIN o.user u
        JOIN o.orderDetails od
        GROUP BY u.id, u.username
        ORDER BY SUM(od.subtotal) DESC
    """)
    List<CustomerSpendDTO> findCustomerSpend();


    /*

    - Agrupar pedidos por restaurante
    - Contar cuántos pedidos tiene cada restaurante
    - Ordenar de mayor a menor

        - La entidad raíz es Order.
        - Cada fila de partida es un pedido.
        - Desde ahí navegamos a Restaurant

    - Order tiene una relación @ManyToOne con Restaurant.
    - JOIN sin tipo explícito = INNER JOIN.
    - Significa: solo cuentan pedidos que tienen restaurante asociado (lo normal si la FK es obligatoria).
    - Aparecen solo restaurantes que tienen al menos un pedido, porque el punto de partida es Order.
    - Un restaurante con 0 pedidos no puede aparecer, aunque uses LEFT JOIN, porque no hay filas de Order desde las que “arrancar”.

    - Se hace una proyección a DTO (no se devuelven entidades)
     */
    // ¿Qué restaurantes tienen más ventas?
    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO(
            r.id,
            r.name,
            COUNT(o.id)
        )
        FROM Order o
        JOIN o.restaurant r
        GROUP BY r.id, r.name
        ORDER BY COUNT(o.id) DESC
    """)
    List<RestaurantOrdersDTO> findTopRestaurantsByOrders();

    // pendiente: mostrar todos los restaurnates, incluidos que no tienen ningún pedido...
    // Está implementado en RestaurantRepository

    /*
        - No es dinero.
        - Es unidades vendidas = suma de quantity de las líneas de pedido.
        - Partimos de Order como entidad raíz porque el dato “vendido” nace de pedidos reales.
        - Aunque haya múltiples pedidos y múltiples líneas para el mismo plato, el SUM las consolida en un único total.
         -Incluye Platos que aparecen en al menos una línea de pedido.
         - Excluye Platos que nunca se han pedido (no aparecen en OrderDetail).
         */
    //¿Cuáles son los platos más vendidos?
    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO(
            d.id,
            d.name,
            SUM(od.quantity)
        )
        FROM Order o
        JOIN o.orderDetails od
        JOIN od.dish d
        GROUP BY d.id, d.name
        ORDER BY SUM(od.quantity) DESC
    """)
    List<DishUnitsSoldDTO> findTopDishesByUnitsSold();



    // PENDIENTE!!!
    //













    // ------------------ de juan -----------
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
