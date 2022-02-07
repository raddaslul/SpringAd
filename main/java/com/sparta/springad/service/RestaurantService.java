package com.sparta.springad.service;

import com.sparta.springad.dto.reponseDto.RestaurantResponseDto;
import com.sparta.springad.dto.requestDto.RestaurantRequestDto;

import com.sparta.springad.repository.RestaurantRepository;
import com.sparta.springad.validator.RestaurantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantValidator restaurantValidator;

    // 식당 등록
    public RestaurantResponseDto registerRestaurant(RestaurantRequestDto restaurantRequestDto){
        restaurantValidator.validateRestaurantInput(restaurantRequestDto);
        return restaurantRepository.save(restaurantRequestDto.toEntity()).toResponseDto();
    }

    // 식당 조회
    public List<RestaurantResponseDto> viewRestaurants(){
        return restaurantRepository.findAll().stream().map(
                restaurant -> restaurant.toResponseDto()).collect(Collectors.toList());
    }
}
