package com.example.demo.Service.impl;

import com.example.demo.Model.Basket;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Repository.BasketRepository;
import com.example.demo.Service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    @Override
    public void addProduct(User user, Product product) {
            Basket basket = getBasket(user).get();
            basket.getProducts().add(product);
            basketRepository.save(basket);
        }

    @Transactional
    @Override
    public void setNewBasketForUser(User user) {
        Optional<Basket> optionalBasket = basketRepository.findBasketByUserOrderByIdDesc(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            basket.setActuality("DONE");
            basketRepository.save(basket);
            basketRepository.save(new Basket(user, new ArrayList<>(), "TRUE"));
        }
    }

    @Transactional
    @Override
    public Optional<Basket> getBasket(User user) {
        if (!basketRepository.findBasketByUserOrderByIdDesc(user).isPresent()) {
            basketRepository.save(new Basket(user, new ArrayList<>(), "TRUE"));
        }
        return basketRepository.findBasketByUserOrderByIdDesc(user);
    }
}
