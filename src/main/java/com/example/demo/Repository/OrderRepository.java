package com.example.demo.Repository;

import com.example.demo.Model.Code;
import com.example.demo.Model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.code=:code")
    Optional<Order> findByCode(@Param("code") Code code);

    List<Order> findAll();

    Order save(Order order);
}
