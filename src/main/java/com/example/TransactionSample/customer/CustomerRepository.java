package com.example.TransactionSample.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE s.card = ?1")
    Optional<Customer> findCustomerById(Long card);

}
