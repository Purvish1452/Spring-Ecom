package com.purvish.ecom_proj.service;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<product> getAllProducts() {
        return repo.findAll();
    }

    public product getProductById(int id) {
        return repo.findById(id).get();
    }

    // Method to add a product along with its image
    public product addProduct(product product, MultipartFile imageFile) {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        return repo.save(product);
    }
}
