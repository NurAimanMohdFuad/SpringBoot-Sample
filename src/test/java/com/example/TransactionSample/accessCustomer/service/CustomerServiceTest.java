package com.example.TransactionSample.accessCustomer.service;

import com.example.TransactionSample.accessCustomer.dao.CustomerRepository;
import com.example.TransactionSample.accessCustomer.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)// to replace autocloseable
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService underTest;

    @BeforeEach // will run before each test, get new instance
    void setUp() {

        underTest = new CustomerService(customerRepository);
    }


    @Test
        //@Disabled //will not run
    void canGetAllCustomer() {
        //when
        underTest.getCustomer();
        //then
        verify(customerRepository).findAll();
    }

    @Test
    void canAddNewCustomer() {
        //given
        Customer customer =new Customer(
                4555_5555_5555_5555L,
                "Julia",
                LocalDate.of(2022, APRIL, 4),
                38.18F

        );
        //when
        underTest.addNewCustomer(customer);

        //then
        ArgumentCaptor<Customer> customerArgumentCaptor =
                ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository)
                .save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer);
    }

    @Test
    void willThrowWhenCardIsTaken() {
        //given
        Customer customer =new Customer(
                4555_5555_5555_5555L,
                "Julia",
                LocalDate.of(2022, APRIL, 4),
                38.18F

        );
        given(customerRepository.userExistsByCard(customer.getCard())).willReturn(true);
//        System.out.println(customerRepository.userExistsByCard(customer.getCard()));
        assertThatThrownBy(()->underTest.addNewCustomer(customer))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldDeleteCustomer() {
        Customer customer = new Customer(
                1L,
                4111_1111_1111_1111L,
                "Alex",
                LocalDate.of(2022, JANUARY, 25),
                1000.50F
        );
        Mockito.when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.of(customer));
        underTest.deleteCustomer(customer.getId());
        verify(customerRepository, times(1)).deleteById(customer.getId());
    }

    @Test
    void willThrowWhenIdNotExistsToDelete() {
        Long id =0L;
        assertThatThrownBy(()-> underTest.deleteCustomer(id))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer with ID " + id +" does not exists");

    }
    @Test
    void updateCustomer() {
        Customer customer =new Customer(1L,
                4555_5555_5555_5555L,
                "Julia",
                LocalDate.of(2022, APRIL, 4),
                38.18F

        );
        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        underTest.updateCustomer(customer.getId(), "Jamilah", 900.50F);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void willThrowWhenIdNotExistsToUpdate() {
        assertThatThrownBy(()-> underTest.updateCustomer(0L,"Jack", 10.40F))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Customer with ID " + 0L + " does not exists");

    }
}