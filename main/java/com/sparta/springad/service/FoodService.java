package com.sparta.springad.service;

import com.sparta.springad.dto.FoodDto;;
import com.sparta.springad.model.Food;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.repository.FoodRepository;
import com.sparta.springad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    // 음식 등록
    public void registerFood(Long restaurantId, List<FoodDto> foodDtoList) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(Exception::new);
        Food food = new Food(foodDtoList, restaurant);
        foodRepository.save(food);
    }
}
