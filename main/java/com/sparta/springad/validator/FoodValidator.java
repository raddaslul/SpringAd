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
        // 음식 가격이 100원 미만이거나 10만원 초과일시 예외 발생
        if (foodRequestDto.getPrice() > 1000000 || foodRequestDto.getPrice() < 100) {
            throw new IllegalArgumentException();
        }

        // 음식 가격이 100원 단위가 아닐 시 예외 발생
        if (!(foodRequestDto.getPrice() % 100 == 0)) {
            throw new IllegalArgumentException();
        }

        // 같은 음식점에 동일한 이름의 음식이 있을 시 예외 발생
        Optional<Food> foods = foodRepository.findFoodByRestaurantAndName(restaurant, foodRequestDto.getName());
        if (foods.isPresent()) {
            throw new IllegalArgumentException();
        }
    }
}

