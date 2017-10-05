package com.example.customer.controller;


import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public String customers(Model model) {

        List<Customer> getCustomer = customerService.get();
        model.addAttribute("customers", getCustomer);

        return "customers";
    }

    @RequestMapping(path = "/add_customer", method = RequestMethod.GET)
    public String addCustomer() {
        return "add_customer";
    }

    @RequestMapping(path = "/add_customer", method = RequestMethod.POST)
    public String newCustomer(@RequestParam(value = "firstname") String firstName,
                              @RequestParam(value = "lastname") String lastName,
                              @RequestParam(value = "email") String email,
                              @RequestParam(value = "phone") String phone) {

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setPhone(phone);
        newCustomer.setEmail(email);
        System.out.println(newCustomer.getFirstName());
        customerService.add(newCustomer);
        return "redirect:/customers";
    }


    @RequestMapping(path = "/view_customer/{customerId}", method = RequestMethod.GET)
    public String viewCustomer(@PathVariable int customerId, Model model) {
        Customer getCustomer = customerService.getById(customerId);
        model.addAttribute("customer", getCustomer);

        return "view_customer";
    }
}
