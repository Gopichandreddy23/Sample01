package com.meditech.service;

import java.util.List;

import com.meditech.dto.ProductDTO;
import com.meditech.entity.Product;

public interface ProductService {
    Product createProduct(ProductDTO dto);
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductDTO dto);
    Product getProductById(Long id);
}