package com.example.demo.Service.impl;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Code;
import com.example.demo.Model.Order;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Transactional
    @Override
    public void addOrder(Code code, String address, String payment, Basket basket) {
        Order order = new Order(address, payment, code, basket);
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    @Transactional
    @Override
    public Optional<Order> getById(long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Order> getByCode(Code code) {
        return orderRepository.findByCode(code);
    }

    @Transactional
    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

}
