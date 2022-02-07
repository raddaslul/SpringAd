package com.sparta.springad.repository;

import com.sparta.springad.model.Food;
import com.sparta.springad.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long id);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String name);
}

