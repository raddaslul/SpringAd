package com.sparta.springad.controller;

import com.sparta.springad.dto.requestDto.FoodRequestDto;
import com.sparta.springad.dto.reponseDto.FoodResponseDto;
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
    public ResponseEntity<List<FoodResponseDto>> registerFood(
            @PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> foodRequestDtoList) {
        List<FoodResponseDto> foodResponseDtoList = foodService.registerFood(restaurantId, foodRequestDtoList);
        if (foodResponseDtoList == null){
            return ResponseEntity.badRequest().body(null);
        } return ResponseEntity.ok().body(null);
    }

    // 음식 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<FoodResponseDto>> viewFoods(@PathVariable Long restaurantId) {
        List<FoodResponseDto> foodResponseDtos = foodService.viewFoods(restaurantId);
        return ResponseEntity.ok().body(foodResponseDtos);
    }
}
