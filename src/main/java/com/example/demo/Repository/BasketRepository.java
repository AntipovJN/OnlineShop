package com.example.demo.Repository;

import com.example.demo.Model.Basket;
import com.example.demo.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    Optional<Basket> findBasketByUserOrderByIdDesc(@Param("user") User user);

}
