package com.sparta.springad.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class RestaurantDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
