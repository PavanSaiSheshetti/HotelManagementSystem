package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.Receptionist;

public interface ReceptionistService {

	
	public boolean addReceptionist(Receptionist receptionist);
	public boolean receptionistLogin(int receptionistId, String receptionistPassword);
	
	public boolean isReceptionistExists(int receptionistId);
	
	public boolean updateReceptionist(Receptionist receptionist);
	
	public Receptionist viewDetails(int receptionistId);
	
	public List<Receptionist> viewAllReceptionists();
	
	public String generatePassword(int min, int max);

	public List<Receptionist> getAllReceptionists();
	public List<Receptionist> getReceptionistByEmail(String receptionistEmail);
	public boolean deleteReceptionist(int receptionistId);
	public Receptionist getReceptionistByReceptionistId(int recptionistId);
	public Receptionist getReceptionistByReceptionistPhoneNumber(String recptionistPhoneNumber);

	public Receptionist getReceptionistByReceptionistEmail(String recptionistEmail);
}
