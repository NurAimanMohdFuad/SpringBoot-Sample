package com.example.TransactionSample.accessCustomer.controller;


import com.example.TransactionSample.accessCustomer.entity.Customer;
import com.example.TransactionSample.accessCustomer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){

        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getCustomer(){

        return customerService.getCustomer();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){

        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(@PathVariable("customerId") Long customerId,
                               @RequestParam(required=false) String name,
                               @RequestParam(required = false) Float balance){
        customerService.updateCustomer(customerId, name, balance);
    }
}



