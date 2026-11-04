package com.purvish.ecom_proj.service;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<product> getAllProducts() {
        return repo.findAll();
    }
}
