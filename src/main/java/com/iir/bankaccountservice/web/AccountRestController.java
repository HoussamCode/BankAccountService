package com.iir.bankaccountservice.web;

import com.iir.bankaccountservice.dto.BankAccountRequestDTO;
import com.iir.bankaccountservice.dto.BankAccountResponseDTO;
import com.iir.bankaccountservice.entities.BankAccount;
import com.iir.bankaccountservice.repositories.BankAccountRepository;
import com.iir.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {

   private  final BankAccountRepository bankAccountRepository;
   private  final AccountService accountService;



    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;

    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Bank account with id %s not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@RequestBody BankAccount bankAccount, @PathVariable String id) {
        BankAccount account =bankAccountRepository.findById(id).orElseThrow();
        if (account.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (account.getType()!=null)account.setType(bankAccount.getType());
        if (account.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        if (account.getCreatedAt()!=null)account.setCreatedAt(new Date());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }

}
