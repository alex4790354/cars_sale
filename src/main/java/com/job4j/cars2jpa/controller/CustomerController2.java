package com.job4j.cars2jpa.controller;

import com.job4j.cars2jpa.entity.Customer;
import com.job4j.cars2jpa.service.CustomerService2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carsjpa/customer")
public class CustomerController2 {

	private CustomerService2 customerService2;

	public CustomerController2(CustomerService2 theCustomerService) {
		customerService2 = theCustomerService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from db
		List<Customer> theCustomers = customerService2.findAll();
		
		// add to the spring model
		theModel.addAttribute("customers", theCustomers);
		
		return "jpa2/customers/list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "jpa2/customers/customers-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		
		// get the customer from the service
		Customer theCustomer = customerService2.findById(theId);
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		// send over to our form
		return "jpa2/customers/customers-form";
	}
	
	
	@PostMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// save the customer
		customerService2.save(theCustomer);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/carsjpa/customer/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService2.deleteById(theId);
		
		// redirect to /carsjpa/list
		return "redirect:/carsjpa/customer/list";
		
	}
}


