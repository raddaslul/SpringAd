package com.sparta.springad.controller;

import com.sparta.springad.dto.reponseDto.OrderDto;
import com.sparta.springad.dto.requestDto.FoodOrderRequestDto;
import com.sparta.springad.dto.requestDto.OrderRequestDto;
import com.sparta.springad.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    // 주문 등록
    @PostMapping("/order/request")
    public OrderDto registerOrder(@RequestBody OrderRequestDto orderRequestDto){
        return orderService.registerOrder(orderRequestDto);
    }
}
