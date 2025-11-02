package com.iir.bankaccountservice.service;

import com.iir.bankaccountservice.dto.BankAccountRequestDTO;
import com.iir.bankaccountservice.dto.BankAccountResponseDTO;

public interface AccountService {
      BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);

      BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
