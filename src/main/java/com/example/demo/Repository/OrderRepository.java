package com.example.demo.Repository;

import com.example.demo.Model.Code;
import com.example.demo.Model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Optional<Order> findByCode(Code code);

    List<Order> findAll();
}
