package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO;
import es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO;
import es.daw.foodexpressapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public List<CustomerSpendDTO> getCustomerSpend() {
        return orderRepository.findCustomerSpend();
    }

    public List<RestaurantOrdersDTO> getTopRestaurantsByOrders() {
        return orderRepository.findTopRestaurantsByOrders();
    }

    public List<DishUnitsSoldDTO> getTopDishesByUnitsSold() {
        return orderRepository.findTopDishesByUnitsSold();
    }
}
