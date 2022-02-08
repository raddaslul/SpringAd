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
import com.sparta.springad.validator.OrderValidator;
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
    private final OrderValidator orderValidator;

    // 주문 등록하기
    @Transactional
    public OrderDto registerOrder(OrderRequestDto orderRequestDto) {
        // 식당 확인하기
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(NullPointerException::new);
       int totalPrice = restaurant.getDeliveryFee(); // 총 금액에 배달비 포함시킨 채로 선언

        // return 값 주기 위한 코드
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();

        // 처음에는 FoodOrder 안에 파라미터를 담을 때 Orders 파라미터는 못 담기 때문에
        // 빌더를 이용해서 다른 파라미터들을 먼저 리스트로 담아주는 작업이 필요
        // 그 때 리스트를 담는 그릇으로 사용하기 위해 선언
        List<FoodOrder> foodOrderList = new ArrayList<>();
        // Orders가 선언이 되면 리스트에 담긴 파라미터들을 반복문을 통해 다시 뽑아낸 후
        // 생성자를 이용하여 모든 파라미터(Orders 포함)들을 값으로 넣어줌

        // 주문요청(OrderRequestDto)에 들어있던 음식리스트(Foods)를
        // 주문상세요청(FoodOrderRequestDto)에 넣어줌
        List<FoodOrderRequestDto> foodOrderRequestDtoList = orderRequestDto.getFoods();

        for (FoodOrderRequestDto foodOrderRequestDto : foodOrderRequestDtoList) {
            // 주문상세요청에 들어있던 음식Id로 음식 정보 가져오기
            Food food = foodRepository.findById(foodOrderRequestDto.getId())
                    .orElseThrow(NullPointerException::new);
            int quantity = foodOrderRequestDto.getQuantity(); // 주문 수량
            int foodPrice = food.getPrice()*quantity; // 주문한 음식가격 * 수량
            totalPrice += foodPrice; // 총 금액

            // 주문내용 유효성검사
            orderValidator.validateOrderInput(quantity, foodPrice, restaurant.getMinOrderPrice());

            FoodOrder foodOrder = FoodOrder.builder() // 빌더를 이용하여 foodOrder(주문상세)에 값 넣어주기
                    .quantity(quantity)
                    .price(foodPrice)
                    .food(food)
                    .build();
            // 빌더를 사용하는 이유 :
            // Orders(주문)도 FoodOrder(상세주문)의 파라미터인데 Orders는 반복문 밖에서 선언되기 때문에
            // 필요한 파라미터만 가져다 쓸 수 있는 빌더를 사용

            // 일단 Orders가 없는 채로 리스트에 저장
            foodOrderList.add(foodOrder);

            // return 값 주기 위한 코드
            FoodOrderDto foodOrderDto = new FoodOrderDto(foodOrder);
            foodOrderDtoList.add(foodOrderDto);
        }
        // 주문(Orders) 저장
        Orders orders = new Orders(totalPrice, restaurant);
        orderRepository.save(orders);

        // 주문 상세 저장
        // 주문 상세에 아까 넣지 못한 Orders 값 넣는 작업
        for (FoodOrder foodOrder : foodOrderList) {
            FoodOrder tempFoodOrder = new FoodOrder( // FoodOrder의 모든 파라미터가 다 들어가기 때문에 생성자 사용
                    foodOrder.getQuantity(), foodOrder.getPrice(), orders, foodOrder.getFood());
            foodOrderRepository.save(tempFoodOrder);
        }

        // return 값 주기 위한 코드
        OrderDto orderDto = new OrderDto(orders, foodOrderDtoList);
        return orderDto;
    }

    // 주문 조회하기
    @Transactional
    public List<OrderDto> viewOrders() {
        // 리턴값 선언
        // OrderDto는 Orders와 FoodOrderDtoList를 파라미터 값으로 가짐
        List<OrderDto> orderDtoList = new ArrayList<>();

        // 주문에 저장된 데이터 모두 가져오기
        List<Orders> ordersList = orderRepository.findAll();

        // OrderDto에 들어갈 파라미터 중 하나인 FoodOrderDtoList를 담을 그릇 선언
        List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();

        for (Orders orders : ordersList) {
            // 주문(orders)을 기준으로 주문상세(foodOrderList) 데이터 가져오기
            List<FoodOrder> foodOrderList = foodOrderRepository.findAllByOrders(orders);
            for (FoodOrder foodOrder : foodOrderList) {
                int quantity = foodOrder.getQuantity();
                int price = foodOrder.getPrice();
                Food food = foodOrder.getFood();

                // foodOrderList 완성시키기
                FoodOrder tempFoodOrder = new FoodOrder(quantity, price, orders, food);
                FoodOrderDto foodOrderDto = new FoodOrderDto(tempFoodOrder);
                foodOrderDtoList.add(foodOrderDto);
            }

            // OrderDto 완성시키기
            OrderDto orderDto = new OrderDto(orders, foodOrderDtoList);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
