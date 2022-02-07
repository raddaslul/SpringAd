package com.sparta.springad.validator;

import com.sparta.springad.dto.requestDto.FoodRequestDto;
import com.sparta.springad.model.Food;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FoodValidator {

    private final FoodRepository foodRepository;

    public void validateFoodInput(FoodRequestDto foodRequestDto, Restaurant restaurant) {
        if (foodRequestDto.getPrice() > 1000000 || foodRequestDto.getPrice() < 100) {
            throw new IllegalArgumentException();
        }

        if (!(foodRequestDto.getPrice() % 100 == 0)) {
            throw new IllegalArgumentException();
        }

        Optional<Food> foods = foodRepository.findFoodByRestaurantAndName(restaurant, foodRequestDto.getName());
        if (foods.isPresent()) {
            throw new IllegalArgumentException();
        }
    }
}

