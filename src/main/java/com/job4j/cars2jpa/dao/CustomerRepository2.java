package com.job4j.cars2jpa.dao;

import com.job4j.cars2jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository2 extends JpaRepository<Customer, Integer> {

	// no need to write any code!

    // add a method to sort by last name
    public List<Customer> findAllByOrderByLastNameAsc();
	
}
