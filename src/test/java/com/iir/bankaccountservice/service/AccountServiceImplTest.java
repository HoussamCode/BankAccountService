package com.iir.bankaccountservice.service;

import com.iir.bankaccountservice.dto.BankAccountRequestDTO;
import com.iir.bankaccountservice.dto.BankAccountResponseDTO;
import com.iir.bankaccountservice.entities.BankAccount;
import com.iir.bankaccountservice.enums.AccountType;
import com.iir.bankaccountservice.mappers.AccountMapper;
import com.iir.bankaccountservice.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAccount() {
        BankAccountRequestDTO requestDTO = new BankAccountRequestDTO();
        requestDTO.setBalance(100.0);
        requestDTO.setType(AccountType.CURRENT_ACCOUNT);
        requestDTO.setCurrency("MAD");

        BankAccount savedAccount = BankAccount.builder()
                .id("1")
                .balance(100.0)
                .type(AccountType.CURRENT_ACCOUNT)
                .currency("MAD")
                .build();

        BankAccountResponseDTO responseDTO = new BankAccountResponseDTO();
        responseDTO.setId("1");
        responseDTO.setBalance(100.0);

        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(savedAccount);
        when(accountMapper.fromBankAccount(savedAccount)).thenReturn(responseDTO);

        BankAccountResponseDTO result = accountService.addAccount(requestDTO);

        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals(100.0, result.getBalance());

        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }
}
