package com.sparta.springad.validator;

import com.sparta.springad.dto.RestaurantDto;

public class RestaurantValidator {
    public static void validateRestaurantInput(RestaurantDto restaurantDto) {
        if (restaurantDto.getMinOrderPrice() > 100000 || restaurantDto.getMinOrderPrice() < 1000) {
            throw new IllegalArgumentException();
        }

        if (!(restaurantDto.getMinOrderPrice() % 100 == 0)) {
            throw new IllegalArgumentException();
        }

        if (restaurantDto.getDeliveryFee() > 10000 || restaurantDto.getDeliveryFee() < 0) {
            throw new IllegalArgumentException();
        }

        if (!(restaurantDto.getDeliveryFee() % 500 == 0)) {
            throw new IllegalArgumentException();
        }
    }
}
