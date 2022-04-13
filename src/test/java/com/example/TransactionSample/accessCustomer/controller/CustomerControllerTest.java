package com.example.TransactionSample.accessCustomer.controller;

import com.example.TransactionSample.accessCustomer.entity.Customer;
import com.example.TransactionSample.accessCustomer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.util.Calendar.APRIL;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    void testGetCustomer() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1L,
                6666_6666_6666_6666L,
                "Aiman",
                LocalDate.of(2022,APRIL,25),
                13.69F));
        Mockito.when(customerService.getCustomer()).thenReturn(customerList);
        String url ="/api/v1/customer";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();

        System.out.println(actualJsonResponse);

        String expectedJsonResponse = objectMapper.writeValueAsString(customerList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void registerNewCustomer() throws Exception {
        Customer customer = new Customer(1L,
                6666_6666_6666_6666L,
                "Aiman",
                LocalDate.of(2022,APRIL,25),
                13.69F);
        mockMvc.perform(post("/api/v1/customer")
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType("application/json;charset=UTF-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



    }

    @Test
    void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/{customerId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer =new Customer(1L,
                4555_5555_5555_5555L,
                "Julia",
                LocalDate.of(2022, Month.APRIL, 4),
                38.18F);
        mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/v1/customer/{customerId}", 1)
                        .content(objectMapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}