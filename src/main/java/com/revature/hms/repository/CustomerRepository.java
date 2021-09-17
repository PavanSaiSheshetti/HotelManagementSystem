package com.revature.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.Customer;



public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	public Customer findByCustomerUserNameAndPassword(String CustomerUserName, String password);
	
	public Customer findByCustomerUserName(String CustomerUserName);

}
