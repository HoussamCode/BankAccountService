package com.iir.bankaccountservice.repositories;

import com.iir.bankaccountservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
