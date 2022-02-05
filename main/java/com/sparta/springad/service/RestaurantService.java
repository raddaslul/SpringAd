package com.sparta.springad.service;

import com.sparta.springad.dto.RestaurantDto;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant registerRestaurant(RestaurantDto restaurantDto) throws Exception {
        Restaurant restaurant = new Restaurant(restaurantDto);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public List<Restaurant> viewRestaurants(){
        return restaurantRepository.findAll();
    }
}
