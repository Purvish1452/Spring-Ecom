package com.purvish.ecom_proj.controller;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String getProduct() {
        return "Product details";
    }

    @GetMapping("/product")
    public List<product> getlistofProducts() {
        return service.getAllProducts();
    }
}
