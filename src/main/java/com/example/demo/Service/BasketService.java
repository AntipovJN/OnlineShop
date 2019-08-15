package com.example.demo.Service;



import com.example.demo.Model.Basket;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;

import java.util.Optional;

public interface BasketService {

    Optional<Basket> getBasket(User user);

    void addProduct(User user, Product product);

    void setNewBasketForUser(User user);

}
