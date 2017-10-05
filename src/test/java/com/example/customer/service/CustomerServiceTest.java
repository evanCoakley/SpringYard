package com.example.customer.service;

import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {


    @Autowired
    CustomerService customerService;

    @Test
    public void testInvalidAdd() {

        Customer customer1 = createTestCustomer();
        Customer customer2 = createTestCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        boolean catchBlockHit = false;

        customer2.setFirstName(null);
        try{
            customerService.add(customers);
        } catch (Exception e) {
            catchBlockHit = true;
        }

        customers = customerService.get();

        Customer foundCustomer1 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
        Assert.assertNull("The first customer created should have rolled back", foundCustomer1);
        Assert.assertTrue(catchBlockHit);
    }
    @Test
    public void testAddGet(){
        Customer customer1 = createTestCustomer();

        customerService.add(customer1);
        List<Customer> customers = customerService.get();

        Customer foundCustomer1 = findInList(customers, customer1.getFirstName(), customer1.getLastName());

        Assert.assertNotNull("The first customer created should have created and found properly", foundCustomer1);
        Assert.assertEquals("Added and found customer should have the same first name", customer1.getFirstName(), foundCustomer1.getFirstName());
        Assert.assertEquals("Added and found customer should have the same last name", customer1.getLastName(), foundCustomer1.getLastName());
;
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


    static Customer findInList(List<Customer> customers, String first, String last) {
        // Find the new person in the list
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
                return customer;
            }
        }
        return null;
    }


}
