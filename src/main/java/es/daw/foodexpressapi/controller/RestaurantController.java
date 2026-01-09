package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.RestaurantRequestDTO;
import es.daw.foodexpressapi.dto.RestaurantResponseDTO;
import es.daw.foodexpressapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDTO>> findAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RestaurantResponseDTO> create(@RequestBody RestaurantRequestDTO restaurantRequestDTO) {

        Optional<RestaurantResponseDTO> result = restaurantService.create(restaurantRequestDTO);
        if (result.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(result.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (restaurantService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> update(@PathVariable Long id,
                                                        @RequestBody RestaurantRequestDTO restaurantRequestDTO) {

        return ResponseEntity.ok(restaurantService.update(id, restaurantRequestDTO));


    }




}
