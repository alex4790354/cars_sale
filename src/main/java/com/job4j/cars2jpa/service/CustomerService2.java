package com.job4j.cars2jpa.service;

import com.job4j.cars2jpa.entity.Customer;

import java.util.List;

public interface CustomerService2 {

	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theEmployee);
	
	public void deleteById(int theId);
	
}
