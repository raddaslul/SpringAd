package com.sparta.springad.dto.requestDto;

import com.sparta.springad.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public Restaurant toEntity(){
        return Restaurant.builder()
                .name(this.name)
                .minOrderPrice(this.minOrderPrice)
                .deliveryFee(this.deliveryFee)
                .build();
    }
}
