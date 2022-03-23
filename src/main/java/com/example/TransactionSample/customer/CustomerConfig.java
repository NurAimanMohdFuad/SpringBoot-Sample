package com.example.TransactionSample.customer;


import antlr.actions.cpp.ActionLexer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repos){
        return args -> {
            Customer alex = new Customer(
                    1L,
                    4111_1111_1111_1111L,
                    "Alex",
                    LocalDate.of(2022, JANUARY, 25),
                    1000.50F
            );
            Customer bobby = new Customer(
                    2L,
                    4222_2222_2222_2222L,
                    "Bobby",
                    LocalDate.of(2022, FEBRUARY, 8),
                    50.67F
            );
            Customer camellia = new Customer(
                    3L,
                    4333_3333_3333_3333L,
                    "Camellia",
                    LocalDate.of(2022, MARCH, 18),
                    905.03F
            );
            Customer dylan = new Customer(
                    4L,
                    4444_4444_4444_4444L,
                    "Dylan",
                    LocalDate.of(2022, JANUARY, 31),
                    15.66F
            );
            Customer emily = new Customer(
                    5L,
                    4388576018402626L,
                    "Emily",
                    LocalDate.of(2022, FEBRUARY, 28),
                    2547.18F
            );
            repos.saveAll(
                    List.of(alex, bobby, camellia, emily)
            );
        };
    }
}
