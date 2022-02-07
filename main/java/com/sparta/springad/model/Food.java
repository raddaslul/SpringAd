package com.sparta.springad.model;

import com.sparta.springad.dto.reponseDto.FoodResponseDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Food {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public FoodResponseDto toResponseDto() {
        return FoodResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .build();
    }
}
