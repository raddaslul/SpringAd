package com.sparta.springad.controller;

import com.sparta.springad.dto.reponseDto.RestaurantResponseDto;
import com.sparta.springad.dto.requestDto.RestaurantRequestDto;
import com.sparta.springad.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public ResponseEntity<RestaurantResponseDto> registerRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto){
        RestaurantResponseDto restaurantResponseDto = restaurantService.registerRestaurant(restaurantRequestDto);
        return ResponseEntity.ok().body(restaurantResponseDto);
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponseDto>> viewRestaurants() {
        List<RestaurantResponseDto> restaurantResponseDtos = restaurantService.viewRestaurants();
        return ResponseEntity.ok().body(restaurantResponseDtos);
    }
}

