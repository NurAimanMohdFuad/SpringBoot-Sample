package com.example.TransactionSample.customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;
    private Long card;
    private String name;
    private LocalDate lastUse;
    private Float balance;

    public Customer() {
    }

    public Customer(Long id,
                    Long card,
                    String name,
                    LocalDate lastUse,
                    Float balance) {
        this.card = card;
        this.name = name;
        this.lastUse = lastUse;
        this.balance = balance;
    }

    public Customer(Long card,
                    String name,
                    LocalDate lastUse,
                    Float balance) {
        this.name = name;
        this.lastUse = lastUse;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastUse() {
        return lastUse;
    }

    public void setLastUse(LocalDate lastUse) {
        this.lastUse = lastUse;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "card=" + card +
                ", name='" + name + '\'' +
                ", lastUse=" + lastUse +
                ", balance=" + balance +
                '}';
    }
}
