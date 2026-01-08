package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.mapper.DishMapper;
import es.daw.foodexpressapi.repository.DishRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishMapper dishMapper;

//    public List<DishResponseDTO> findAll() {
//        return dishRepository.findAll()
//                .stream()
//                .map(dishMapper::toResponseDTO)
//                .toList();
//    }

    public Page<DishResponseDTO> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable)
                .map(dishMapper::toResponseDTO);
    }

}
