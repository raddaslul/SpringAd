package com.sparta.springad.dto.reponseDto;

import lombok.*;

@Getter
@Setter
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
