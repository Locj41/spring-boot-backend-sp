package com.springboot.demo.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.models.Product;
import com.springboot.demo.repository.ProductRepository;


import com.springboot.demo.utils.ResponseObject;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping(path = "/getAllProducts")
    ResponseEntity<ResponseObject> getAllProducts() {
        List<Product> data = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("ok","Query product successfully",data));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> data = repository.findById(id);

        return data.isPresent()
                ? ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ok", "Query product successfully", data))
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("failed", "Query product failed", data));

    }

    @PostMapping(path = "/createProduct")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseObject("ok", "insert data success", repository.save(product)));
    }
}
