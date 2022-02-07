package com.sparta.springad.dto.reponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FoodResponseDto {
    private Long id;
    private String name;
    private int price;
}
