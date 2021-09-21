package com.revature.hms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Receptionist;
import com.revature.hms.repository.ReceptionistRepository;


@Service
public class ReceptionistServiceImpl implements ReceptionistService{

	
	@Autowired
	ReceptionistRepository receptionistRepository;
	
	@Autowired
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean receptionistLogin(int receptionistId, String receptionistPassword) {
		Optional<Receptionist> receptionistData = receptionistRepository.findByReceptionistIdAndReceptionistPassword(receptionistId, receptionistPassword);
		return receptionistData.isPresent();

		 
	}
	
	@Override
	public boolean addReceptionist(Receptionist receptionist) {
		/*
		 * List password=
		 * em.createNamedStoredProcedureQuery("getUniquePassword").getResultList();
		 * String receptionistPassword= (String) password.get(0);
		 */
		String receptionistPassword = generatePassword(8,16);
		System.out.println("Random Password:" +receptionistPassword);
		receptionist.setReceptionistPassword(receptionistPassword);
		System.out.println("Adding Receptionist called");
		if(receptionistRepository.save(receptionist) != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isReceptionistExists(int receptionistId) {
		Optional<Receptionist> receptionistData = receptionistRepository.findById(receptionistId); 
		return receptionistData.isPresent();
	}

	@Override
	public boolean updateReceptionist(Receptionist receptionist) {
		receptionistRepository.save(receptionist);
		return true;
	}

	@Override
	public Receptionist viewDetails(int receptionistId) {
		Optional<Receptionist> receptionistData = receptionistRepository.findById(receptionistId);
		Receptionist receptionist = receptionistData.get();
		return receptionist;
	}


	@Override
	public String generatePassword(int min, int max) {
		Random random = new Random();
		String upperCase ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String specialChars="!@#$%&*(){}?";
		String all = upperCase+lowerCase+number+specialChars;
		List<Character> lettersList = new ArrayList<Character>();
		for(char c: all.toCharArray()) {
			lettersList.add(c);
		}
		Collections.shuffle(lettersList);
		String receptionistPassword="";
		for(int i=random.nextInt(max-min)+min; i>0; --i) {
			receptionistPassword += lettersList.get(random.nextInt(lettersList.size()));
		}
		return receptionistPassword;
		
	}

	@Override
	public List<Receptionist> viewAllReceptionists() {
		List<Receptionist> receptionistData = receptionistRepository.findAll();
		return receptionistData;
	}





	@Override
	public boolean deleteReceptionist(int receptionistId) {
		receptionistRepository.deleteById(receptionistId);
		return true;
	}

	@Override
	public Receptionist getReceptionistByReceptionistId(int recptionistId) {
		Optional<Receptionist> receptionistData = receptionistRepository.findById(recptionistId);
		Receptionist receptionist = receptionistData.get();
		return receptionist;
	}
	@Override
	public List<Receptionist> getAllReceptionists() {
		return (List<Receptionist>) receptionistRepository.findAll();
	}
	@Override
	public Receptionist getReceptionistByReceptionistPhoneNumber(String recptionistPhoneNumber) {
		Receptionist receptionistData= receptionistRepository.findByReceptionistPhoneNumber(recptionistPhoneNumber); 
		return receptionistData;
	}

	@Override
	public Receptionist getReceptionistByReceptionistEmail(String recptionistEmail) {
		Receptionist receptionistData = receptionistRepository.findByReceptionistEmail(recptionistEmail);
		
		return receptionistData;
	}

	@Override
	public List<Receptionist> getReceptionistByEmail(String receptionistEmail) {
		// TODO Auto-generated method stub
		return null;
	}


}
