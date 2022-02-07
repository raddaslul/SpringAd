package com.sparta.springad.service;

import com.sparta.springad.dto.requestDto.FoodRequestDto;;
import com.sparta.springad.dto.reponseDto.FoodResponseDto;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.repository.FoodRepository;
import com.sparta.springad.repository.RestaurantRepository;
import com.sparta.springad.validator.FoodValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodValidator foodValidator;

    // 음식 등록
    @Transactional
    public void registerFood(Long restaurantId, List<FoodRequestDto> foodRequestDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(NullPointerException::new);
        foodRequestDtoList.stream().forEach(foodRequestDto -> {
            foodValidator.validateFoodInput(foodRequestDto, restaurant);
            foodRequestDto.setRestaurantId(restaurantId);
            foodRepository.save(foodRequestDto.toEntity(restaurant));
        });
    }

    // 음식 조회
    @Transactional
    public List<FoodResponseDto> viewFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(NullPointerException::new);
        return foodRepository.findAllByRestaurantId(restaurant.getId()).stream().map(
                food -> food.toResponseDto()).collect(Collectors.toList());
    }
}
