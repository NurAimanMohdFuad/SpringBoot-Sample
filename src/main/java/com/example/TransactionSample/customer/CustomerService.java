package com.example.TransactionSample.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.
                findCustomerById(customer.getCard());
        if(customerOptional.isPresent()){
            throw new IllegalStateException("Card is already taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if(!exists){
            throw new IllegalStateException(
                    "Customer with ID" + customerId + "does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, Float balance) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new IllegalStateException(
                        "Student with ID" + customerId + "does not exist"
                ));
        if (name != null && name.length() > 0 && !Objects.equals(customer.getName(), name)){
            customer.setName(name);
        }

        if( balance != null && !Objects.equals(customer.getBalance(), balance)){
            customer.setBalance(balance);
        }
    }
}
