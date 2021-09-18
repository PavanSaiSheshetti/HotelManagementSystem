package com.revature.hms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HotelBookingSystemApplication;
import com.revature.hms.model.Admin;
import com.revature.hms.model.Receptionist;
import com.revature.hms.service.AdminService;
import com.revature.hms.service.ReceptionistService;



@RestController
@RequestMapping("adminController")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController 
{
	@Autowired
	ReceptionistService receptionistService;
	
	@Autowired
	HotelBookingSystemApplication mailApplication;
	
	@Autowired
	AdminService adminService;
    
	@PostMapping("/addReceptionist")
	public ResponseEntity<String> addReceptionist (@RequestBody Receptionist receptionist)
		throws AddressException, MessagingException, IOException
	{
		ResponseEntity<String> responseEntity = null;
		String message = null;
		String from = "Taj-Restaurant";
		 String subject="Registration Status";
		 int receptionistId = receptionist.getReceptionistId();
		 if(receptionistService.isReceptionistExists(receptionistId))
		{
		    message = "Receptionist with " +receptionistId+ " already exists";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		else
		{
			receptionistService.addReceptionist(receptionist);
			int receptionistId1 = receptionist.getReceptionistId();
			 Receptionist receptionist1 = receptionistService.viewDetails(receptionistId1);
				String toReceiver = receptionist1.getReceptionistEmail();
				message = "Congrajulations! and Hearty Welcome to the Taj Restaurant,"+
						"\n Dear "+receptionist1.getReceptionistName()+" ,admin has registered your details successfully"+
						"\n Please use  this details for login"+  
						"\n  ReceptionistId: "+receptionist1.getReceptionistId() +
				          "\n ReceptionistPassword: "+receptionist1.getReceptionistPassword()+
				          "\n And your salary details are below: "+
				          "\n Salary: " +receptionist1.getSalary()+
				          "\n Wishing u all the best";
				mailApplication.sendMail(from, toReceiver, subject, message);
				responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		System.out.println(receptionist);
		return responseEntity;
	}
	
	@PutMapping
	public ResponseEntity<String> updateMyProfile(@RequestBody Receptionist receptionist){
	   ResponseEntity<String> responseEntity = null;
	   int receptionistId= receptionist.getReceptionistId();
	   String message= null;
	   String from = "Taj-Restaurant";
	   String subject="Registration Status";
	   if(receptionistService.isReceptionistExists(receptionistId)) {
		   receptionistService.updateReceptionist(receptionist);
		   Receptionist receptionist1 = receptionistService.viewDetails(receptionistId);
		   String toReceiver = receptionist1.getReceptionistEmail();
		   message="Dear "+receptionist1.getReceptionistName()+" ,admin has updated your details successfully"+
				  
		           "\n Here is your Salary and WorkExperience Details check it "+
		           "\n Your Salary is updated and your current salary is:" +receptionist1.getSalary()+
				   "\n You have successfully completed: "+receptionist1.getExperience() +" years of work with us"+
		           "\n Continue your work with us";   
		   mailApplication.sendMail(from, toReceiver, subject, message);
		   responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
	   }
	   else {
		   message="Receptionist with ReceptionistId" +receptionistId+ "does not exist";
		   responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
	   }
	   return responseEntity;
	}
	
	@GetMapping("/searchByAdminIdAndAdminPassword/{adminId}/{adminPassword}")
	public ResponseEntity<Admin> adminLogin(@PathVariable("adminId") int adminId, @PathVariable("adminPassword") String adminPassword){
		
			 ResponseEntity<Admin> responseEntity=null;
			Admin admin =new Admin();
			 boolean res=false;		
			 res=adminService.adminLogin(adminId,adminPassword);
			 if(res) {
			admin=adminService.getAdminById(adminId); 
			responseEntity=new ResponseEntity<Admin> (admin,HttpStatus.OK);
			 System.out.println("logged successfully");
			 } 
			 else {
			 responseEntity=new ResponseEntity<Admin> (admin,HttpStatus.OK);
			 System.out.println("Your login details are not matched");
			 }
			
			 return responseEntity;
			  	
		}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Receptionist>> getAllReceptionists(){
		ResponseEntity<List<Receptionist>> responseEntity = null;
		List<Receptionist> receptionistList= receptionistService.getAllReceptionists();
		if(receptionistList.size()==0) {
			responseEntity=new ResponseEntity<List<Receptionist>>(receptionistList,HttpStatus.NO_CONTENT);
		}
		else {
			responseEntity=new ResponseEntity<List<Receptionist>>(receptionistList,HttpStatus.OK);
		}
		return responseEntity;
	}

	
	@GetMapping("/{receptionistId}")
	public ResponseEntity<Receptionist> getReceptionistById(@PathVariable("receptionistId") int receptionistId){
		ResponseEntity<Receptionist> responseEntity = null;
		Receptionist receptionist = new Receptionist();
		if(receptionistService.isReceptionistExists(receptionistId)) {
			receptionist = receptionistService.getReceptionistByReceptionistId(receptionistId);
			responseEntity = new ResponseEntity<Receptionist>(receptionist,HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<Receptionist>(receptionist,HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	@DeleteMapping("/{receptionistId}")
	public ResponseEntity<String> deleteReceptionist(@PathVariable("receptionistId") int recptionistId){
		/*
		 * ResponseEntity<String> responseEntity = null; String message = null;
		 * if(receptionistService.isReceptionistExists(recptionistId)) { Receptionist
		 * rec = receptionistService.getReceptionistByReceptionistId(recptionistId);
		 * String mail = rec.getReceptionistEmail(); System.out.println("###"+mail);
		 * receptionistService.deleteReceptionist(recptionistId); message =
		 * "Receptionist with receptionistId:"+recptionistId+"deleted successfully";
		 * 
		 * MailSend.sendMail(mail,"Receptionist :", message); responseEntity = new
		 * ResponseEntity<String>(message,HttpStatus.OK); } else { message =
		 * "Receptionist with receptionistId:"+recptionistId+"not deleted successfully";
		 * responseEntity = new ResponseEntity<String>(message,HttpStatus.CONFLICT); }
		 * return responseEntity;
		 */
	    
	    ResponseEntity<String> responseEntity = null;
		String message = null;
		String from = "Taj-Restaurant";
		 String subject="Registration Status";
		 Receptionist rec = receptionistService.getReceptionistByReceptionistId(recptionistId);
		 int receptionistId = rec.getReceptionistId();
		 if(receptionistService.isReceptionistExists(receptionistId))
		{
			
			 
				 Receptionist receptionist1 = receptionistService.viewDetails(receptionistId);
					String toReceiver = receptionist1.getReceptionistEmail();
					message = "Sorry ! for the inconvenience,"+
							"\n Dear "+receptionist1.getReceptionistName()+" ,your account has been deleted by admin due to some reasons."+
							"\n Please contact  management.hotel51@gmail.com for further details"+  
							
					          "\n Thanking You";
					mailApplication.sendMail(from, toReceiver, "Regarding removal of your account", message);
				
					 receptionistService.deleteReceptionist(receptionistId);
			
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		else
		{
			 message = "Receptionist with " +receptionistId+ " doesn't exists";
				responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		System.out.println(rec);
		return responseEntity;
	}

@GetMapping("/phnno/{receptionistPhoneNumber}")
	public ResponseEntity<Receptionist> getReceptionistByPhoneNumber(@PathVariable("receptionistPhoneNumber") String receptionistPhoneNumber){
		ResponseEntity<Receptionist> responseEntity = null;
		System.out.println("View receptionist By Phonenumber called");
	
		 Receptionist receptionist = receptionistService.getReceptionistByReceptionistPhoneNumber(receptionistPhoneNumber);
		 
		 	responseEntity = new ResponseEntity<Receptionist>(receptionist,HttpStatus.OK);
		 
		return responseEntity;
	}
	@GetMapping("/email/{receptionistEmail}")
	public ResponseEntity<Receptionist> getReceptionistByEmail(@PathVariable("receptionistEmail") String receptionistEmail){
		ResponseEntity<Receptionist> responseEntity = null;
		System.out.println("View receptionist By receptionistEmail called");
	
		 Receptionist receptionist = receptionistService.getReceptionistByReceptionistEmail(receptionistEmail);
		 
		 	responseEntity = new ResponseEntity<Receptionist>(receptionist,HttpStatus.OK);
		 
		return responseEntity;
	}
}
