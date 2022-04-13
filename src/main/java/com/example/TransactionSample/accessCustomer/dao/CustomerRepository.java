package com.example.TransactionSample.accessCustomer.dao;

import com.example.TransactionSample.accessCustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT s FROM Customer s WHERE s.card = ?1")
    Optional<Customer> findCustomerById(Long card);

    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Customer c " +
            "WHERE c.card = ?1"
    )
    Boolean userExistsByCard(Long card);

}
