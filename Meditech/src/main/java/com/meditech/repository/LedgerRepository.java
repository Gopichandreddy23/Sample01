package com.meditech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meditech.entity.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
    List<Ledger> findByProductId(Long productId);
}