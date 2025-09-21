package com.meditech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meditech.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long >{

}
