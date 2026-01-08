package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
//    public ResponseEntity<List<DishResponseDTO>> getAllDishes() {
//        return ResponseEntity.ok(dishService.findAll());
//    }
    public ResponseEntity<Page<DishResponseDTO>> getAllDishes(Pageable pageable) {
        return ResponseEntity.ok(dishService.findAll(pageable));
    }

}

