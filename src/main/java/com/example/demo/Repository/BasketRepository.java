package com.example.demo.Repository;

import com.example.demo.Model.Basket;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {

    Basket save (Basket basket);

    Optional<Basket> findById(Long id);

    @Query("SELECT b FROM Basket b WHERE b.user =:user AND actuality = 'TRUE'")
    Optional<Basket> findBasketByUserOrderByIdDesc(@Param("user") User user);

//    void addProduct(Basket basket, Product product);

//    void deleteBasketByUser(User user);

}
