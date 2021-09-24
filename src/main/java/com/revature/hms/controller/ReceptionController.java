package com.revature.hms.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelBookingSystemApplication;
import com.revature.hms.model.Bill;
import com.revature.hms.model.Booking;
import com.revature.hms.model.Receptionist;
import com.revature.hms.model.Wallet;
import com.revature.hms.service.BookingHistoryService;
import com.revature.hms.service.BookingService;
import com.revature.hms.service.BookingServiceImpl;
import com.revature.hms.service.ReceptionistService;
import com.revature.hms.service.WalletService;

@RestController
@RequestMapping("/Reception")
@CrossOrigin(origins = "http://localhost:4200")
public class ReceptionController {

	Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	WalletService walletService;

	@Autowired
	BookingService bookingService;
	
	  @Autowired
		HotelBookingSystemApplication mailApplication;

	@Autowired
	private BookingHistoryService bookingHistoryService;

	@Autowired
	private ReceptionistService receptionistService;
	
	@GetMapping("requests/{cancellationStatus}")
	public ResponseEntity<List<Booking>> getCancellations(@PathVariable String cancellationStatus){
		
		ResponseEntity<List<Booking>> responseEntity = null;
		List<Booking> bookingsList = bookingService.viewCancellations(cancellationStatus);
		if(bookingsList.size() == 0) {
			responseEntity = new ResponseEntity<List<Booking>>(bookingsList,HttpStatus.NOT_FOUND);
		}
		else {
			responseEntity = new ResponseEntity<List<Booking>>(bookingsList,HttpStatus.OK);
		}
		
		return responseEntity;
		
	}

	@PutMapping
	public ResponseEntity<String> updateMyProfile(@RequestBody Receptionist receptionist) {
		ResponseEntity<String> responseEntity = null;
		int receptionistId = receptionist.getReceptionistId();
		String message = null;
		if (receptionistService.isReceptionistExists(receptionistId)) {
			receptionistService.updateReceptionist(receptionist);
			// Receptionist receptionist1 = receptionistService.viewDetails(receptionistId);
			message = "Receptionist with ReceptionistId " + receptionistId + " details has been Updated Successfully ";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {
			message = "Receptionist with ReceptionistId" + receptionistId + "does not exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}

	@GetMapping("/searchByReceptionistIdAndReceptionistPassword/{receptionistId}/{receptionistPassword}")
	public ResponseEntity<Receptionist> receptionistLogin(@PathVariable("receptionistId") int receptionistId,
			@PathVariable("receptionistPassword") String receptionistPassword) {

		ResponseEntity<Receptionist> responseEntity = null;
		Receptionist receptionist = null;
		boolean res = false;
		res = receptionistService.receptionistLogin(receptionistId, receptionistPassword);
		if (res) {
			// receptionist= receptionistService.viewDetails(receptionistId);
			responseEntity = new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);
			System.out.println("logged successfully");
		} else {
			responseEntity = new ResponseEntity<Receptionist>(receptionist, HttpStatus.CONFLICT);
			System.out.println("Your login details are not matched");
		}

		return responseEntity;

	}

	@GetMapping("/searchByReceptionistId/{receptionistId}")
	public ResponseEntity<Receptionist> getReceptionistById(@PathVariable("receptionistId") int receptionistId) {
		ResponseEntity<Receptionist> responseEntity = null;
		Receptionist receptionist = new Receptionist();
		if (receptionistService.isReceptionistExists(receptionistId)) {
			receptionist = receptionistService.viewDetails(receptionistId);
			responseEntity = new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Receptionist>(receptionist, HttpStatus.OK);

		}
		return responseEntity;
	}

	@PostMapping("/save/{userName}/{money}")
	public ResponseEntity<String> addWallet(@PathVariable("userName") String userName,
			@PathVariable("money") int money) {

		ResponseEntity<String> responseEntity = null;
		Wallet wallet=new Wallet(userName,money);
		walletService.addWallet(wallet);
		responseEntity = new ResponseEntity<String>("wallet added successfully", HttpStatus.OK);
		return responseEntity;

	}

	@SuppressWarnings("deprecation")
	@PutMapping("/{username}/{dateIn}")
	ResponseEntity<Boolean> addMoneyForCancellation(@PathVariable("username") String username,
			@PathVariable("dateIn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateIn) throws ParseException {
		ResponseEntity<Boolean> responseEntity = null;

		Booking booking = bookingService.findByUserName(username);
		String message = null;
		String from = "naveedimran2802@gmail.com";
		String to = booking.getEmail();
		String subject = "Regarding the cancellation request";
		Date currentDate = new Date();

		int monthDifference = dateIn.getMonth() - currentDate.getMonth();
		System.out.println(monthDifference);

		int dateDifference = dateIn.getDate() - currentDate.getDate();
		int price = booking.getRoomPrice();
		System.out.println(dateDifference);
		if (monthDifference != 0) {
			dateDifference += 31;
			System.out.println(dateDifference);
			if (dateDifference > 31) {
				int amount = (price / 100) * 10;
				Boolean result = walletService.addMoneyForCancellation(username, amount);
				LOGGER.info("Booking Cancelled and money added to wallet successfully");
				if (result) {
					LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

					// need to update the record into booking history then delete record in db.
					bookingHistoryService.addToHistory(username);
					bookingService.deleteRecord(username);

					message = "Dear " + username + ","
							+ "\n Your cancellation request has been succesfully processed and your booking has been cancelled successfully"
							+ "\nYour payment during the booking of your hotel room has been refunded successfully to yourrespective wallet."
							+ "\nThank you for thinking about us for your hotel needs"
							+ "\nPlease query us at menando@gmail.com, we would love to hear from you" + "\n Regards"
							+ "\n Menando resort";
					walletService.sendMail(from, to, subject, message);
					responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
				} else {
					LOGGER.error("Problem occured during cancellation");
				}
			}
		}

		if (monthDifference == 0 && (dateDifference >= 0 && dateDifference <= 3)) {
			System.out.println("no refund is called");
			int amount = 0;
			Boolean result = walletService.addMoneyForCancellation(username, amount);
			if (result) {
				LOGGER.info("Booking Cancelled successfully");
				LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

				// need to update the record into booking history then delete record in db.
				bookingHistoryService.addToHistory(username);
				bookingService.deleteRecord(username);
				message = "Dear " + username + "," + "\n"
						+ "\n Your cancellation request has been succesfully processed and your booking "
						+ "\n has been cancelled successfully"
						+ "\nYour payment during the booking of you hotel room has not been refunded due to the hotel policies."
						+ "\n\nPlease query us at " + "menando@gmail.com, we would love to hear from you" + "\n \n"
						+ "Regards" + "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);

			} else {
				LOGGER.error("Problem occured during cancellation");
			}
		}

		else if ((monthDifference == 0 || monthDifference == 1) && (dateDifference >= 4 && dateDifference <= 30)) {
			int amount = (price / 100) * 5;
			System.out.println("amount for  greater than 4 days and lesser than 30 is :" + amount);
			Boolean result = walletService.addMoneyForCancellation(username, amount);
			if (result) {
				LOGGER.info("Booking Cancelled and money added to wallet successfully");
				LOGGER.info("******************** DELETED CANCELLED ROOM RECORD FROM DATABASE");

				// need to update the record into booking history then delete record in db.
				bookingHistoryService.addToHistory(username);
				bookingService.deleteRecord(username);
				message = "Dear " + username + "," + "\n"
						+ "\n Your cancellation request has been succesfully processed and your booking has been cancelled successfully"
						+ "Your payment during the booking of you hotel room has been refunded successfully to your respective wallet."
						+ "\n Due to policies and conditions only 5% of your payed amount has been refunded to your wallet"
						+ "\n\nThank you for thinking about us for your hotel needs" + "\n \nPlease query us at "
						+ "menando@gmail.com, we would love to hear from you" + "\n \n" + "Regards"
						+ "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);
			} else {
				LOGGER.error("Problem occured during cancellation");
			}
		}

		return responseEntity;

	}

	@PutMapping("/updateWallet/{customerUserName}/{walletAmount}")
	public ResponseEntity<String> updateMoneyToWallet(@PathVariable String customerUserName,
			@PathVariable int walletAmount) {// Working
		ResponseEntity<String> responseEntity = null;

		String message = null;
		if (walletService.addMoneyForCancellation(customerUserName, walletAmount)) {
			message = "Amount Updated Successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		} else {

			message = "Something Went Wromg";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}
	
	
	@PutMapping("/updatePrice/{customerUserName}/{price}")
	public ResponseEntity<Boolean> updatePrice(@PathVariable String customerUserName,
			@PathVariable int price) {// Working
		ResponseEntity<Boolean> responseEntity = null;

		boolean result = bookingService.updatePrice(price, customerUserName);
		if(result) {
			responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);

		}
		else {
			responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.BAD_REQUEST);

		}
		return responseEntity;
	}
	
	

	@PutMapping("status/{userName}/{status}")
	public ResponseEntity<String> updateStatus(@PathVariable String userName, @PathVariable String status) {

		ResponseEntity<String> responseEntity = null;
		
		Booking booking = bookingService.findByUserName(userName);
		int price = booking.getRoomPrice();
		if (status.compareToIgnoreCase("booked") == 0) {

			price = (price / 100) * 10;
			System.out.println(price);
			Wallet wallet = walletService.getCustomerBalance(userName);
			int customerMoney = wallet.getMoney();
			System.out.println(customerMoney);
			if(price>customerMoney) {
				System.out.println("Inside sufficinet");
				responseEntity = new ResponseEntity<String>("insufficient balance", HttpStatus.CONFLICT);
			}
			else {
		
			boolean result = walletService.deductMoney(userName, price);
			bookingService.updateStatus(userName, status);
			
			if (result) {
				String from = "naveedimran2802@gmail.com";
				String to = booking.getEmail();
				String subject = "Thank you for booking with us";
				String message = "Dear " + userName + "," + "\n"
						+ "\n Thank you for making an booking with us your booking has been processed"
						+ "Your payment during the booking of you hotel room has been reducted successfully from your respective wallet."
						+ "\n\nThank you for thinking about us for your hotel needs" + "\n \nPlease query us at "
						+ "menando@gmail.com, we would love to hear from you" + "\n \n" + "Regards"
						+ "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<String>("10% money deducted", HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<String>("some problem occured", HttpStatus.CONFLICT);

			}
			}
			
			//responseEntity = new ResponseEntity<String>("some problem occured", HttpStatus.OK);
		} else if (status.compareToIgnoreCase("IN") == 0) {
			price = (price / 100) * 40;
			Wallet wallet = walletService.getCustomerBalance(userName);
			int customerMoney = wallet.getMoney();
			if(price>customerMoney) {
				responseEntity = new ResponseEntity<String>("insufficient balance", HttpStatus.CONFLICT);
			}
			else {
			boolean result = walletService.deductMoney(userName, price);
			bookingService.updateStatus(userName, status);
			if (result) {
				String from = "naveedimran2802@gmail.com";
				String to = booking.getEmail();
				String subject = "Thank you for booking with us";
				String message = "Dear " + userName + "," + "\n"
						+ "\n Thank you for Checking in with your booking, your booking status has been processed"
						+ "Your payment during the checkin of the hotel room has been reducted successfully from your respective wallet."
						+ "\n\nThank you for thinking about us for your hotel needs" + "\n \nPlease query us at "
						+ "menando@gmail.com, we would love to hear from you" + "\n \n" + "Regards"
						+ "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<String>("40% money deducted", HttpStatus.OK);
			}
			}
		} else if (status.compareToIgnoreCase("OUT") == 0) {
			price = (price / 100) * 50;
			Wallet wallet = walletService.getCustomerBalance(userName);
			int customerMoney = wallet.getMoney();
			if(price>customerMoney) {
				responseEntity = new ResponseEntity<String>("insufficient balance", HttpStatus.CONFLICT);
			}
			else {
			
			boolean result = walletService.deductMoney(userName, price);
			bookingService.updateStatus(userName, status);
			
			if (result) {
				String from = "naveedimran2802@gmail.com";
				String to = booking.getEmail();
				String subject = "Thank you for booking with us";
				String message = "Dear " + userName + "," + "\n"
						+ "\n Thank you for Checking out with your booking, your booking status has been processed"
						+ "Your payment during the checkout of the hotel room has been reducted successfully from your respective wallet."
						+ "\n\nThank you for thinking about us for your hotel needs" + "\n \nPlease query us at "
						+ "menando@gmail.com, we would love to hear from you" + "\n \n" + "Regards"
						+ "\n Menando resort";
				walletService.sendMail(from, to, subject, message);
				responseEntity = new ResponseEntity<String>("50% percent money deducted", HttpStatus.OK);
			}
			}

		}
		else {
			responseEntity = new ResponseEntity<String>("some problem occured", HttpStatus.BAD_REQUEST);
		}
		

		
		return responseEntity;
	}
	
	@PostMapping("/addBill/{userName}")
	public ResponseEntity<Boolean> addBill(@RequestBody Bill bill,@PathVariable String userName){
		ResponseEntity<Boolean> responseEntity = null;
		System.out.println(bill);
		boolean result = receptionistService.addBill(bill);
		bookingHistoryService.addToHistory(userName);
		bookingService.deleteRecord(userName);

		if(result) {
			responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.OK);

		}
		else {
			responseEntity = new ResponseEntity<Boolean>(result, HttpStatus.CONFLICT);

		}
		
		return responseEntity;
		
		
	}

	@SuppressWarnings("unused")
	@GetMapping("/{customerUserName}/getBalance")
	public ResponseEntity<Integer> getCustomerBalance(@PathVariable String customerUserName) {
		ResponseEntity<Integer> responseEntity = null;

		Wallet wallet = walletService.getCustomerBalance(customerUserName);
		int amount = wallet.getMoney();
		if (wallet != null) {

			amount = wallet.getMoney();

			responseEntity = new ResponseEntity<Integer>(amount, HttpStatus.OK);
		} else {

			amount = 0;
			responseEntity = new ResponseEntity<Integer>(amount, HttpStatus.NOT_FOUND);

		}
		return responseEntity;
	}
	

@GetMapping("/forgetpassword/{receptionistEmail}")
	public ResponseEntity<String> forgetPassword(@PathVariable String receptionistEmail){
		ResponseEntity<String> responseEntity = null;
		 String message= null;
		 String from = "Taj-Restaurant";
		 String subject="Registration Status";
		 Receptionist receptionist = null; 
		 receptionist = receptionistService.getReceptionistByReceptionistEmail(receptionistEmail);
	      if(receptionist!=null) {
		 String toReceiver = receptionist.getReceptionistEmail();
		   message="Dear "+receptionist.getReceptionistName()+" ,"+ 
		           "\n Request has been placed and your login details are "+
				   "\n Your login Id is:"+receptionist.getReceptionistId()+
		           "\n Your Password is:" +receptionist.getReceptionistPassword()+
		           "\n Continue your work with us";   
		   mailApplication.sendMail(from, toReceiver, subject, message);
		    responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
	      }else 
	    	  
	    	  responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	      return responseEntity;
	}

}
