package com.iir.bankaccountservice.web;

import com.iir.bankaccountservice.dto.BankAccountRequestDTO;
import com.iir.bankaccountservice.dto.BankAccountResponseDTO;
import com.iir.bankaccountservice.entities.BankAccount;
import com.iir.bankaccountservice.entities.Customer;
import com.iir.bankaccountservice.repositories.BankAccountRepository;
import com.iir.bankaccountservice.repositories.CustomerRepository;
import com.iir.bankaccountservice.service.AccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {
    private final BankAccountRepository accountRepository;
    private final AccountService accountService;
    private final CustomerRepository customerRepository;

    public BankAccountGraphQLController(BankAccountRepository accountRepository, AccountService accountService, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public List<BankAccount> accountList() {
        return accountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank account %s not found", id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id ,@Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public void deleteAccount (@Argument String id){
        accountRepository.deleteById(id);
    }
    @QueryMapping
    public List<Customer>customers(){
        return customerRepository.findAll();
    }
}


