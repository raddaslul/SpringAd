package com.sparta.springad.model;

import com.sparta.springad.dto.reponseDto.OrderDto;
import com.sparta.springad.dto.requestDto.OrderRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Orders {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    private List<FoodOrder> foodOrderList;

    public Orders(int totalPrice, Restaurant restaurant) {
        this.totalPrice = totalPrice;
        this.restaurant = restaurant;
    }
}
