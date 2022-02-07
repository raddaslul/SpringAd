package com.sparta.springad.service;

import com.sparta.springad.dto.reponseDto.FoodResponseDto;
import com.sparta.springad.dto.reponseDto.OrderDto;
import com.sparta.springad.dto.requestDto.FoodOrderRequestDto;
import com.sparta.springad.dto.requestDto.OrderRequestDto;
import com.sparta.springad.model.Food;
import com.sparta.springad.model.FoodOrder;
import com.sparta.springad.model.Orders;
import com.sparta.springad.model.Restaurant;
import com.sparta.springad.repository.FoodOrderRepository;
import com.sparta.springad.repository.FoodRepository;
import com.sparta.springad.repository.OrderRepository;
import com.sparta.springad.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final FoodOrderRepository foodOrderRepository;

    // 주문 등록하기
    @Transactional
    public OrderDto registerOrder(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(NullPointerException::new);
        int totalPrice = restaurant.getDeliveryFee();

        List<FoodOrder> foodOrderList = new ArrayList<>(); // orders(주문)를 foodOrder(주문 상세)에 넣어주기 위해 선언

        List<FoodResponseDto> foodResponseDtoList = new ArrayList<>(); // return 값 주기 위한 코드

        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoods();
        for (FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {
            Food food = foodRepository.findById(foodOrderRequestDto.getId())
                    .orElseThrow(NullPointerException::new);
            int quantity = foodOrderRequestDto.getQuantity();
            int price = food.getPrice();
            totalPrice += price * quantity;

            FoodOrder foodOrder = FoodOrder.builder() // 빌더를 이용하여 foodOrder(주문상세)에 값 넣어주기
                    .quantity(quantity)
                    .price(price*quantity)
                    .food(food)
                    .build();

            foodOrderList.add(foodOrder);

            FoodResponseDto foodResponseDto = foodOrder.toResponseDto(); // return 값 주기 위한 코드
            foodResponseDto.setPrice(price*quantity); // return 값 주기 위한 코드
            foodResponseDtoList.add(foodResponseDto); // return 값 주기 위한 코드
        }

        // 주문 마스터 저장
        Orders orders = new Orders(totalPrice, restaurant);
        orderRepository.save(orders);

        // 주문 상세 저장
        for (FoodOrder foodOrder : foodOrderList) {
            FoodOrder tempFoodOrder = FoodOrder.builder()
                    .orders(orders)
                    .quantity(foodOrder.getQuantity())
                    .price(foodOrder.getPrice())
                    .food(foodOrder.getFood())
                    .build();

            foodOrderRepository.save(tempFoodOrder);
        }

        OrderDto orderDto = new OrderDto(orders, foodResponseDtoList); // return 값 주기 위한 코드
        return orderDto; // return 값 주기 위한 코드
    }
}
