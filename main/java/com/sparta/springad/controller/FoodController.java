package com.sparta.springad.controller;

import com.sparta.springad.dto.FoodDto;
import com.sparta.springad.repository.RestaurantRepository;
import com.sparta.springad.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class FoodController {

    private FoodService foodService;
    RestaurantRepository restaurantRepository;

    // 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> foodDtoList) throws Exception {
        foodService.registerFood(restaurantId, foodDtoList);
    }
}
