package com.sparta.springad.service;

import com.sparta.springad.dto.reponseDto.FoodOrderDto;
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
        // 식당 확인하기
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(NullPointerException::new);

        // 객체 생성시 필요한 파라미터들
        int quantity = 0; int foodPrice = 0;
        int totalPrice = restaurant.getDeliveryFee(); // 총 금액에 배달비 포함시킨 채로 선언
        Food food = new Food();

        // return 값 주기 위한 코드
        List<FoodOrderDto> foods = new ArrayList<>();

        // 주문요청(OrderRequestDto)에 들어있던 주문들어온 음식리스트(Foods)를
        // 주문상세요청(FoodOrderRequestDto)에 넣어줌
        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoods();

        for (FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {
            food = foodRepository.findById(foodOrderRequestDto.getId())
                    .orElseThrow(NullPointerException::new);
            quantity = foodOrderRequestDto.getQuantity();
            foodPrice = food.getPrice()*quantity;
            totalPrice += foodPrice;

            FoodOrder foodOrder = FoodOrder.builder() // 빌더를 이용하여 foodOrder(주문상세)에 값 넣어주기
                    .quantity(quantity)
                    .price(foodPrice)
                    .food(food)
                    .build();
            // 빌더를 사용하는 이유 :
            // Orders(주문)도 FoodOrder(상세주문)의 파라미터인데 Orders는 반복문 밖에서 선언해 줄 것이기 때문에
            // 필요한 파라미터만 가져다 쓸 수 있는 빌더를 사용해줌

            // return 값 주기 위한 코드
            FoodOrderDto foodOrderDto = new FoodOrderDto(foodOrder);
            foods.add(foodOrderDto);
        }

        // 주문 마스터 저장
        Orders orders = new Orders(totalPrice, restaurant);
        orderRepository.save(orders);

        // 주문 상세 저장
        FoodOrder foodOrder = new FoodOrder(quantity, foodPrice, orders, food);
        foodOrderRepository.save(foodOrder);

        // return 값 주기 위한 코드
        OrderDto orderDto = new OrderDto(orders, foods);
        return orderDto;
    }
}
