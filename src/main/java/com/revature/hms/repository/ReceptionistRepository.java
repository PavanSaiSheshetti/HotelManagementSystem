package com.revature.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.revature.hms.model.Receptionist;


public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer>
{
	
//	  @Procedure 
//	  String generate_Password();
	
	public List<Receptionist> findByReceptionistEmail(String receptionistEmail);
	 
}
