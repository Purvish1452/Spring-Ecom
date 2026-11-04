package com.purvish.ecom_proj.controller;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/product")
    public ResponseEntity<List<product>> getlistofProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }
}
