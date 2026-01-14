package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public List<CustomerSpendDTO> getCustomerSpend(){
        return orderRepository.findCustomerSpend();
    }

}
