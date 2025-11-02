package com.iir.bankaccountservice;

import com.iir.bankaccountservice.entities.BankAccount;
import com.iir.bankaccountservice.entities.Customer;
import com.iir.bankaccountservice.enums.AccountType;
import com.iir.bankaccountservice.repositories.BankAccountRepository;
import com.iir.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class Tp1jeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp1jeeApplication.class, args);

    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        return args -> {
            Stream.of("Mohammed","Yassine","Hanae","Imane").forEach(c -> {
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(c -> {

                for (int i = 1; i <= 10; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .balance(Math.random() * 90000)
                            .createdAt(new Date())
                            .currency("MAD")
                            .customer(c)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });




        };
    }
}
