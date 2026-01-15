package es.daw.foodexpressapi.controller;

/*
¿Cuánto ha gastado cada cliente?

Gasto total por cliente (suma de subtotales).

GET /api/reports/customers/spend

¿Qué restaurantes tienen más ventas?

Restaurantes con más pedidos (COUNT de órdenes)

GET /api/reports/restaurants/top-by-orders
¿Cuáles son los platos más vendidos?

Platos más vendidos por unidades (SUM de quantity)

GET /api/reports/dishes/top-by-units
 */

import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO;
import es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO;
import es.daw.foodexpressapi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

//    ¿Cuánto ha gastado cada cliente?
//    Gasto total por cliente (suma de subtotales).
//    GET /api/reports/customers/spend
    @GetMapping("/customers/spend")
    public ResponseEntity<List<CustomerSpendDTO>> getCustomerSpend() {
        return ResponseEntity.ok(reportService.getCustomerSpend());
    }

    // GET /api/reports/restaurants/top-by-orders
    @GetMapping("/restaurants/top-by-orders")
    public ResponseEntity<List<RestaurantOrdersDTO>> getTopRestaurantsByOrders() {
        return ResponseEntity.ok(reportService.getTopRestaurantsByOrders());
    }
    // GET /api/reports/dishes/top-by-units
    // MEJORAS!!!
    // -limit opcional (por defecto)
    //- @Validation.... no puede ser superior a 100
    // - no debería usar limit sino usar paginación!!!! usaría size
    @GetMapping("/dishes/top-by-units")
    public ResponseEntity<List<DishUnitsSoldDTO>> getTopDishesByUnitsSold(
            @RequestParam( defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(
                reportService.getTopDishesByUnitsSold().stream().limit(limit).toList()
        );
    }
}
