package com.meditech.service;

import java.util.List;

import com.meditech.dto.LedgerDTO;
import com.meditech.entity.Ledger;

public interface LedgerService {
    Ledger addPayment(LedgerDTO dto);
    List<Ledger> getLedgerByProductId(Long productId);
}
