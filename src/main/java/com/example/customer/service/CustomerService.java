package com.example.customer.service;

import com.example.customer.model.Customer;


import java.util.List;


public interface CustomerService {
    Customer add(Customer customer);
    List<Customer> add(List<Customer> customers);
    Customer getById(int id);
    List<Customer> get();
    Customer update(Customer customer);
    void delete(int id);

}
