package com.sparta.springad.controller;

import com.sparta.springad.dto.FoodRequestDto;
import com.sparta.springad.dto.FoodResponseDto;
import com.sparta.springad.repository.RestaurantRepository;
import com.sparta.springad.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Object> registerFood(
            @PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDtoList) {
        foodService.registerFood(restaurantId, foodRequestDtoList);
        return ResponseEntity.ok().body(null);
    }

    // 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> viewFoods(@PathVariable Long restaurantId) {
        return foodService.viewFoods(restaurantId);
    }
}
