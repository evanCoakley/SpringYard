package com.example.customer.service;

import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest extends Customer{


    @Autowired
    CustomerService customerService;

    @Test
    public void testAddGet() {

        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());
        String phone = "512-317-2252";
        String email = "afiftiesbluesdreamer@hotmail.com";

        Customer customer1 = createTestCustomer();
        customer1.setFirstName(firstName);
        customer1.setLastName(lastName);
        customer1.setPhone(phone);
        customer1.setEmail(email);
        customerService.add(customer1);

        List<Customer> customers = customerService.get();

        Customer customer2 = findInList(customers, firstName, lastName, phone, email);
        Assert.assertNotNull(customer2);

        Customer customer3 = customerService.getById(customer2.getId());
        Assert.assertNotNull(customer3);
        Assert.assertEquals(firstName, customer3.getFirstName());
        Assert.assertEquals(lastName, customer3.getLastName());
        Assert.assertEquals(phone, customer3.getPhone());
        Assert.assertEquals(email, customer3.getEmail());
    }


    // utils.........
    static Customer createTestCustomer() {
        // Get unique names every time this test runs
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }


    static Customer findInList(List<Customer> customers, String first, String last, String phone, String email) {
        // Find the new person in the list
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(first) && customer.getLastName().equals(last) && customer.getPhone().equals(phone)
                    && customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }


}
