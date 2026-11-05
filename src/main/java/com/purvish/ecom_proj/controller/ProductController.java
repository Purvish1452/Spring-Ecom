package com.purvish.ecom_proj.controller;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173"})
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping({"/product", "/products"})
    public ResponseEntity<List<product>> getlistofProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<product> getProductById(@PathVariable int id) {
        product found = service.getProductById(id);
        return found == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(found);
    }

    @PostMapping({"/product", "/products"})
    public ResponseEntity<?> addProduct(
            @RequestPart("product") product product,
            @RequestPart("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().body("An image file is required");
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(product, imageFile));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to read the uploaded image");
        }
    }

    @GetMapping({"/product/{productId}/image", "/products/{productId}/image"})
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        product found = service.getProductById(productId);
        if (found == null || found.getImageData() == null || found.getImageData().length == 0) {
            return ResponseEntity.notFound().build();
        }
        MediaType imageType;
        try {
            imageType = MediaType.parseMediaType(found.getImageType());
        }
        catch (IllegalArgumentException | NullPointerException e) {
            imageType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity.ok().contentType(imageType).body(found.getImageData());
    }

    @PutMapping({"/product/{id}", "/products/{id}"})
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestPart("product") product product,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            product updated = service.updateProduct(id, product, imageFile);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to read the uploaded image");
        }
    }

    @DeleteMapping({"/product/{id}", "/products/{id}"})
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (service.getProductById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping({"/product/search", "/products/search"})
    public ResponseEntity<List<product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchProducts(keyword));
    }
}
