package com.sparta.springad.controller;

import com.sparta.springad.dto.RestaurantDto;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.service.RestaurantService;
import lombok.AllArgsConstructor;
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
    public Restaurant registerRestaurant(@RequestBody RestaurantDto restaurantDto) throws Exception {
        Restaurant restaurant = restaurantService.registerRestaurant(restaurantDto);
        return restaurant;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> viewRestaurants() {
        return restaurantService.viewRestaurants();
    }
}

