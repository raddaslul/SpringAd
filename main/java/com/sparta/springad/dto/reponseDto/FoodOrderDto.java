package com.sparta.springad.dto.reponseDto;

import com.sparta.springad.model.FoodOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {
    String name;
    int quantity;
    int price;

    public FoodOrderDto(FoodOrder foodOrder) {
        this.name = foodOrder.getFood().getName();
        this.quantity = foodOrder.getQuantity();
        this.price = foodOrder.getFood().getPrice()*quantity;
    }
}
