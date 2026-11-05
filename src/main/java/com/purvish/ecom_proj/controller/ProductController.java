package com.purvish.ecom_proj.controller;

import com.purvish.ecom_proj.model.product;
import com.purvish.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart product product, @RequestPart MultipartFile imageFile)
    {
         try {
             product product1=service.addProduct(product, imageFile);
             return new ResponseEntity<>(product1, HttpStatus.CREATED);
         }
         catch (Exception e) {
             return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping("/product/{productId}/image")
  public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {

        product product = service.getProductById(productId);
        byte[]  imageFile = product.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
  }


  public ResponseEntity<String> updateProduct(@PathVariable int id , @RequestPart product product, @RequestPart MultipartFile imageFile) {

        try {

            product product1 = service.updateProduct(id, product, imageFile);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(product!=null)
        {
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
  }
}
