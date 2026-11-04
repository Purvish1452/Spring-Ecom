package com.purvish.ecom_proj.repo;

import com.purvish.ecom_proj.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<product, Integer> {


}
