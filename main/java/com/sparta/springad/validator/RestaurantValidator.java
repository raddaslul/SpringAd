package com.sparta.springad.validator;

import com.sparta.springad.dto.requestDto.RestaurantRequestDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantValidator {
    public void validateRestaurantInput(RestaurantRequestDto restaurantRequestDto) {
        // 식당 최소주문 금액이 1000원 미만, 10만원 초과일 시 예외 발생
        if (restaurantRequestDto.getMinOrderPrice() > 100000 || restaurantRequestDto.getMinOrderPrice() < 1000) {
            throw new IllegalArgumentException();
        }

        // 식당 최소주문 금액이 100원 단위가 아닐 시 예외 발생
        if (!(restaurantRequestDto.getMinOrderPrice() % 100 == 0)) {
            throw new IllegalArgumentException();
        }

        // 식당 배달비가 0원 미만, 1만원 초과일 시 예외 발생
        if (restaurantRequestDto.getDeliveryFee() > 10000 || restaurantRequestDto.getDeliveryFee() < 0) {
            throw new IllegalArgumentException();
        }

        // 식당 배달비가 500원 단위가 아닐 시 예외 발생
        if (!(restaurantRequestDto.getDeliveryFee() % 500 == 0)) {
            throw new IllegalArgumentException();
        }
    }
}
