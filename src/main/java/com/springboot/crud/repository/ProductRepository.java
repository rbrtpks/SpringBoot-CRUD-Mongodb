package com.springboot.crud.repository;

import com.springboot.crud.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends MongoRepository <Product, String> {
    Optional<Product> findByNameProduct(String nameProduct);
    ArrayList<Product> findAllByOrderByCreatedAtDesc();
}
