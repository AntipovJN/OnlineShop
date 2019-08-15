package com.example.demo.Service;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Code;
import com.example.demo.Model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    void addOrder(Code code, String address, String payment, Basket basket);

    Optional<Order> getById(long id);

    Optional<Order> getByCode(Code code);

    List<Order> getAll();

    void updateOrder(Order order);

    void removeOrder(Order order);
}
