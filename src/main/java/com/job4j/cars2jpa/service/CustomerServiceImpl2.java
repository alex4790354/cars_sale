package com.job4j.cars2jpa.service;

import com.job4j.cars2jpa.dao.CustomerRepository2;
import com.job4j.cars2jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl2 implements CustomerService2 {

	private CustomerRepository2 customerRepository;

	@Autowired
	public CustomerServiceImpl2(CustomerRepository2 theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Customer findById(int theId) {
		Optional<Customer> result = customerRepository.findById(theId);

		Customer theCustomer = null;

		if (result.isPresent()) {
			theCustomer = result.get();
		} else {
			// we didn't find the customer
			throw new RuntimeException("Did not find customer id - " + theId);
		}

		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		customerRepository.save(theCustomer);
	}

	@Override
	public void deleteById(int theId) {
		customerRepository.deleteById(theId);
	}

}

