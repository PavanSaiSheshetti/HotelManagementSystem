package com.revature.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.Bill;


public interface BillRepository extends CrudRepository<Bill, Integer> {
	public List<Bill> findByCustomerUserName(String customerUserName);
}
