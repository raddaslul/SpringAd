package com.sparta.springad.repository;

import com.sparta.springad.model.FoodOrder;
import com.sparta.springad.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    List<FoodOrder> findAllByOrders(Orders orders);
}
