package com.pdk.productservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pdk.productservice.Model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
