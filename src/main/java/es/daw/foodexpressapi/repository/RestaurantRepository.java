package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO;
import es.daw.foodexpressapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO(
            r.id,
            r.name,
            COUNT(o.id)
        )
        FROM Restaurant r
            LEFT JOIN r.orders o
                GROUP BY r.name, r.id
                    ORDER BY COUNT(o.id) DESC
    """
    )
    List<RestaurantOrdersDTO> findAllRestaurantsWithOrdersCount();
    //List<RestaurantOrdersDTO> findRestaurantOrdersByRestaurantId(Long restaurantId); // -- necesita añadir el WHERE

}
