package com.sparta.springad.controller;

import com.sparta.springad.dto.reponseDto.OrderDto;
import com.sparta.springad.dto.requestDto.OrderRequestDto;
import com.sparta.springad.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<OrderDto> registerOrder(@RequestBody OrderRequestDto orderRequestDto){
        OrderDto orderDto = orderService.registerOrder(orderRequestDto);
        return ResponseEntity.ok().body(orderDto);
    }

    // 주문 조회
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> viewOrders() {
        List<OrderDto> orderDtoList = orderService.viewOrders();
        return ResponseEntity.ok().body(orderDtoList);
    }
}
