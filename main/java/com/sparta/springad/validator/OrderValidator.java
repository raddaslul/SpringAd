package com.sparta.springad.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    @Transactional
    public void validateOrderInput(int quantity, int foodPrice, int minOrderPrice) {
        // 주문한 음식 수량이 1 미만, 100 초과일 시 예외 발생
        if (quantity < 1 || quantity > 100) {
            throw new IllegalArgumentException();
        }

        // 주문한 총 금액이 식당 최소주문금액보다 작을 시 예외 발생
        if (foodPrice < minOrderPrice) {
            throw new IllegalArgumentException();
        }
    }
}
