package com.sparta.springad.dto.requestDto;

import com.sparta.springad.model.Food;
import com.sparta.springad.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FoodRequestDto {
    private Long restaurantId;
    private String name;
    private int price;

    public Food toEntity(Restaurant restaurant){
        return Food.builder()
                .restaurant(restaurant)
                .name(this.name)
                .price(this.price)
                .build();
    }
}
