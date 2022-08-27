package com.fabricedev.productserv.repository;

import com.fabricedev.productserv.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product , String> {
}
