package com.meditech.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meditech.dto.LedgerDTO;
import com.meditech.entity.Ledger;
import com.meditech.service.LedgerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @PostMapping
    public Ledger addPayment(@RequestBody LedgerDTO dto) {
        return ledgerService.addPayment(dto);
    }

    @GetMapping("/{productId}")
    public List<Ledger> getLedger(@PathVariable Long productId) {
        return ledgerService.getLedgerByProductId(productId);
    }
}