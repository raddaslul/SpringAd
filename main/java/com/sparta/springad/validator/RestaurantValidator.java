package com.sparta.springad.validator;

import com.sparta.springad.dto.requestDto.RestaurantRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public void validateRestaurantInput(RestaurantRequestDto restaurantRequestDto) {
        if (restaurantRequestDto.getMinOrderPrice() > 100000 || restaurantRequestDto.getMinOrderPrice() < 1000) {
            throw new IllegalArgumentException();
        }

        if (!(restaurantRequestDto.getMinOrderPrice() % 100 == 0)) {
            throw new IllegalArgumentException();
        }

        if (restaurantRequestDto.getDeliveryFee() > 10000 || restaurantRequestDto.getDeliveryFee() < 0) {
            throw new IllegalArgumentException();
        }

        if (!(restaurantRequestDto.getDeliveryFee() % 500 == 0)) {
            throw new IllegalArgumentException();
        }
    }
}
