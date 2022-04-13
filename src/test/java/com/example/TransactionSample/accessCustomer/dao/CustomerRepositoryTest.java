package com.example.TransactionSample.accessCustomer.dao;

import com.example.TransactionSample.accessCustomer.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.Optional;
import static java.time.Month.APRIL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll(); //After each test, it will delete everything
    }

    @Test
    void itShouldFindCustomerById() {
        //given
        Long card = 4555_5555_5555_5555L;
        Customer customer =new Customer(
                card,
                "Julia",
                LocalDate.of(2022, APRIL, 4),
                38.18F

        );
        underTest.save(customer);

        //when
        Optional<Customer> optionalCustomer = underTest.findCustomerById(customer.getCard());

        //then
        assertThat(optionalCustomer).isNotEmpty(); //not confident

    }
    @Test
    void itShouldNotFindCustomerById() {
        //given
        Long card = 4555_5555_5555_5555L;

        //when
        Optional<Customer> optionalCustomer = underTest.findCustomerById(card);

        //then
        assertThat(optionalCustomer).isEmpty();


    }
}