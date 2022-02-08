package com.sparta.springad.dto.reponseDto;

import com.sparta.springad.model.Orders;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;

    public OrderDto(Orders orders, List<FoodOrderDto> foods) {
        this.restaurantName = orders.getRestaurant().getName();
        this.foods = foods;
        this.deliveryFee = orders.getRestaurant().getDeliveryFee();
        this.totalPrice = orders.getTotalPrice();
    }
}
