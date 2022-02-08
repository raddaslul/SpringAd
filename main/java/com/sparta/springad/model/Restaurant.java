package com.sparta.springad.model;

import com.sparta.springad.dto.reponseDto.RestaurantResponseDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList;

    @OneToMany(mappedBy = "restaurant")
    private List<Orders> ordersList;

    public RestaurantResponseDto toResponseDto(){
        return RestaurantResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .minOrderPrice(this.minOrderPrice)
                .deliveryFee(this.deliveryFee)
                .build();
    }
}
