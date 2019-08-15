package com.example.demo.Service.impl;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public void add(String name, String description, double price)
            throws IllegalArgumentException, NumberFormatException {
        validateProductData(name, description, price);
        productRepository.save(new Product(name, description, price));
    }

    @Transactional
    @Override
    public void updateProduct(Long id, String name, String description, double price)
            throws IllegalArgumentException, NumberFormatException {
        validateProductData(name, description, price);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            productRepository.save(product);
        }
    }

    @Transactional
    @Override
    public void removeProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            log.error(String.format("Failed remove product with id = '%s'. It is not exist", id));
        }
    }

    @Transactional
    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }


    private void validateProductData(String name, String description, double price)
            throws IllegalArgumentException, NumberFormatException {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("Name field cant be empty");
        }
        if (Objects.isNull(description)
                || description.isEmpty()) {
            throw new IllegalArgumentException("Description field cant be empty");
        }
        if (price < 0) {
            throw new NumberFormatException("Price must be biggest than 0");
        }
    }
}
