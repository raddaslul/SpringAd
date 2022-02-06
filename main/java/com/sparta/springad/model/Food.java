package com.sparta.springad.model;

import com.sparta.springad.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    public Food(List<FoodDto> foodDtoList, Restaurant restaurant) {
        for (FoodDto foodDto : foodDtoList){
            this.id = foodDto.getId();
            this.name = foodDto.getName();
            this.price = foodDto.getPrice();
            this.restaurant = restaurant;
        }
    }
}
