package com.example.KTCK.repository;

import com.example.KTCK.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository <Products , Long> {
}
