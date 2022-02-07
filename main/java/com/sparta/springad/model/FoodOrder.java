package com.sparta.springad.model;

import com.sparta.springad.dto.reponseDto.FoodResponseDto;
import com.sparta.springad.dto.requestDto.FoodOrderRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public FoodResponseDto toResponseDto(){
        return FoodResponseDto.builder()
                .id(food.getId())
                .name(food.getName())
                .build();
    }
}
