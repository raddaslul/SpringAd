package com.sparta.springad.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.springad.dto.RestaurantDto;
import com.sparta.springad.validator.RestaurantValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant (RestaurantDto restaurantDto) throws Exception {

        RestaurantValidator.validateRestaurantInput(restaurantDto);

        this.id = restaurantDto.getId();
        this.name = restaurantDto.getName();
        this.minOrderPrice = restaurantDto.getMinOrderPrice();
        this.deliveryFee = restaurantDto.getDeliveryFee();
    }
}