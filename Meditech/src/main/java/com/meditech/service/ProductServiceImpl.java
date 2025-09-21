package com.meditech.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meditech.dto.ProductDTO;
import com.meditech.entity.Product;
import com.meditech.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDTO dto) {
        BigDecimal gstAmount = dto.getPrice()
                .multiply(BigDecimal.valueOf(dto.getGstPercentage()))
                .divide(BigDecimal.valueOf(100));

        BigDecimal total = dto.getPrice().add(gstAmount);

        Product product = Product.builder()
                .name(dto.getName())
                .customerName(dto.getCustomerName())
                .price(dto.getPrice())
                .gstPercentage(dto.getGstPercentage())
                .totalAmount(total)
                .paymentStatus(dto.getPaymentStatus())
                .orderDate(LocalDate.now())
                .build();

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(dto.getName());
        product.setCustomerName(dto.getCustomerName());
        product.setPrice(dto.getPrice());
        product.setGstPercentage(dto.getGstPercentage());
        BigDecimal gstAmount = dto.getPrice()
                .multiply(BigDecimal.valueOf(dto.getGstPercentage()))
                .divide(BigDecimal.valueOf(100));
        product.setTotalAmount(dto.getPrice().add(gstAmount));
        product.setPaymentStatus(dto.getPaymentStatus());
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}