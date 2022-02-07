package com.sparta.springad.controller;

import com.sparta.springad.dto.requestDto.FoodRequestDto;
import com.sparta.springad.dto.reponseDto.FoodResponseDto;
import com.sparta.springad.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController {

    private final FoodService foodService;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(
            @PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDtoList) {
        foodService.registerFood(restaurantId, foodRequestDtoList);
    }

    // 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> viewFoods(@PathVariable Long restaurantId) {
        return foodService.viewFoods(restaurantId);
    }
}
