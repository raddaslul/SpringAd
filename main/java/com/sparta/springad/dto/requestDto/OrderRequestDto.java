package com.sparta.springad.dto.requestDto;

import com.sparta.springad.model.Food;
import com.sparta.springad.model.FoodOrder;
import com.sparta.springad.model.Orders;
import com.sparta.springad.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequestDto {
    private Long restaurantId;
    private List<FoodOrderRequestDto> foods;
}
