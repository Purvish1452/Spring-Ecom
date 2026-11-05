package com.purvish.ecom_proj.service;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<product> getAllProducts() {
        return repo.findAll();
    }

    public product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public product addProduct(product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public product updateProduct(int id, product product, MultipartFile imageFile) throws IOException {
        product existing = getProductById(id);
        if (existing == null) {
            return null;
        }

        existing.setName(product.getName());
        existing.setDesc(product.getDesc());
        existing.setBrand(product.getBrand());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setReleaseDate(product.getReleaseDate());
        existing.setAvailable(product.isAvailable());
        existing.setQuantity(product.getQuantity());
        if (imageFile != null && !imageFile.isEmpty()) {
            existing.setImageName(imageFile.getOriginalFilename());
            existing.setImageType(imageFile.getContentType());
            existing.setImageData(imageFile.getBytes());
        }
        return repo.save(existing);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
