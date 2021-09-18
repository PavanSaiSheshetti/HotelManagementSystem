package com.revature.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hms.model.Receptionist;


public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer>
{
	
//	  @Procedure 
//	  String generate_Password();
	public Receptionist findByReceptionistPhoneNumber(String receptionistPhoneNumber);
	public Receptionist findByReceptionistEmail(String receptionistEmail);
	public Optional<Receptionist> findByReceptionistIdAndReceptionistPassword(int receptionistId, String receptionistPassword);
}
