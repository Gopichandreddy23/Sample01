package com.meditech.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meditech.dto.LedgerDTO;
import com.meditech.entity.Ledger;
import com.meditech.entity.Product;
import com.meditech.repository.LedgerRepository;
import com.meditech.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {

    private final LedgerRepository ledgerRepository;
    private final ProductRepository productRepository;

    @Override
    public Ledger addPayment(LedgerDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Ledger> payments = ledgerRepository.findByProductId(product.getId());
        double paidSoFar = payments.stream()
                .mapToDouble(p -> p.getAmountPaid().doubleValue())
                .sum();

        double remaining = product.getTotalAmount().doubleValue() - paidSoFar - dto.getAmountPaid().doubleValue();

        Ledger ledger = Ledger.builder()
                .product(product)
                .productName(product.getName())
                .amountPaid(dto.getAmountPaid())
                .remainingAmount(remaining < 0 ? BigDecimal.ZERO : BigDecimal.valueOf(remaining))
                .paymentDate(LocalDate.now())
                .build();

        if (remaining <= 0) {
            product.setPaymentStatus("FULL");
        } else {
            product.setPaymentStatus("PARTIAL");
        }
        productRepository.save(product);

        return ledgerRepository.save(ledger);
    }

    @Override
    public List<Ledger> getLedgerByProductId(Long productId) {
        return ledgerRepository.findByProductId(productId);
    }
}