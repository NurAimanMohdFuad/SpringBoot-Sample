package com.example.TransactionSample.accessCustomer.service;


import com.example.TransactionSample.accessCustomer.dao.CustomerRepository;
import com.example.TransactionSample.accessCustomer.entity.Customer;
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

    public Customer addNewCustomer(Customer customer) {
        if(customerRepository.userExistsByCard(customer.getCard())){
            throw new IllegalStateException("Card is already taken");
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        if(customerRepository.findById(customerId).isEmpty()){
            throw new IllegalStateException(
                    "Customer with ID " + customerId + " does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, Float balance) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerId);
        if(foundCustomer.isPresent()){
            Customer customer = foundCustomer.get();
            if (name != null && name.length() > 0 && !Objects.equals(customer.getName(), name)){
                customer.setName(name);
            }

            if( balance != null && !Objects.equals(customer.getBalance(), balance)){
                customer.setBalance(balance);
            }
            customerRepository.save((customer));
        }

        else {
            throw new IllegalStateException(
                    "Customer with ID " + customerId + " does not exists"
            );
        }
    }
}
