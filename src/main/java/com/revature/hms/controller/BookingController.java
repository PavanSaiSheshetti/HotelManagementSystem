package com.revature.hms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HotelBookingSystemApplication;
import com.revature.hms.model.Booking;
import com.revature.hms.model.BookingHistory;
import com.revature.hms.service.BookingHistoryService;
import com.revature.hms.service.BookingService;
import com.revature.hms.service.BookingServiceImpl;

@RestController
@RequestMapping("bookRoom")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

	Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);

	boolean result = false;
	@Autowired
	HotelBookingSystemApplication mailApplication;
	
	@Autowired
	BookingHistoryService bookingHistoryService;
	
	@Autowired
	BookingService bookingService;
	
	@DeleteMapping("/history/{bookingId}")
	public ResponseEntity<Boolean> deleteHistory(@PathVariable int bookingId){
		  ResponseEntity<Boolean> responseEntity = null;
		  boolean result = bookingHistoryService.deleteHistory(bookingId);
		  if(result) {
			  responseEntity = new ResponseEntity<Boolean>(result,HttpStatus.OK);
		  }
		  else {
			  responseEntity = new ResponseEntity<Boolean>(result,HttpStatus.CONFLICT);
		  }
		return responseEntity; 

	}

	@GetMapping("bookingHistory")
	public ResponseEntity<List<BookingHistory>> getBookingsHistory(){
	  ResponseEntity<List<BookingHistory>> responseEntity = null; 
	  List<BookingHistory> bookingsList = bookingService.getBookingsHistory();
	  if(bookingsList.size() == 0) { 
		  responseEntity = new ResponseEntity<List<BookingHistory>>(bookingsList,HttpStatus.NOT_FOUND); 
		  }
	  else { 
		  responseEntity = new ResponseEntity<List<BookingHistory>>(bookingsList,HttpStatus.OK);
		  }
	  
	  return responseEntity; 
	  }

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings() {
		ResponseEntity<List<Booking>> responseEntity = null;
		List<Booking> bookings = bookingService.viewBookingRecords();

		LOGGER.info("******************** LIST OF BOOKING RECORDS");

		if (bookings.size() == 0) {
			LOGGER.info("******************** SIZE OF BOOKING RECORDS = 0");

			responseEntity = new ResponseEntity<List<Booking>>(bookings, HttpStatus.NO_CONTENT);

		} else {
			LOGGER.info("********************  BOOKING RECORD IS PRESENT ");

			responseEntity = new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);

		}
		return responseEntity;
	}
	
	@GetMapping("/RetrieveBookingRoom/{roomNumber}")    //room number> 0 
	public List<Booking> allocatedRooms(@PathVariable("roomNumber")int roomNumber)
	{	LOGGER.info("******************** LIST OF CUSTOMER DETAILS GREATER THAN 0 ROOM NUMBERS");
	
		return bookingService.viewBookedRooms(roomNumber);

	}

	@GetMapping("/cancelledRooms/{cancellation}")	//cancellations = yes
	public List<Booking>cancelledRooms(@PathVariable("cancellation")String cancellation)
	{
		LOGGER.info("******************** LIST OF CANCELLED ROOMS DETAILS FROM BOOKING RECORDS ");
		return bookingService.viewCancellations(cancellation);
	}
	
	@GetMapping("/bookingByUserName/{customerUserName}")
	public Booking getBookingByUserName(@PathVariable("customerUserName")String customerUserName)
	{
		LOGGER.info("******************** LIST OF BOOKED ROOMS DETAILS FROM USERNAME BOOKING RECORDS ");
		return bookingService.findByUserName(customerUserName);
	}
	
	@PutMapping("/updateBookingByRoomNumber/{roomNumber}/{userName}")
	public ResponseEntity<Booking> updateBookingRecord(@PathVariable("roomNumber")int roomNumber,@PathVariable("userName")String userName)
	{
		System.out.println(" not upda");
		ResponseEntity<Booking> responseEntity = null;
		Booking booking = new Booking();
		int bookingRoom =roomNumber;
		String message=null;
		LOGGER.info("******************** ROOM NUMBER IS ALLOCATED TO CUSTOMER ");

		if (bookingService.isRoomNumberExists(bookingRoom))// 200
		{
			LOGGER.info("******************** ROOM IS FILLED");
			
		message = "Room : "+bookingRoom+" 	already allocated, Please allocate other room";
		responseEntity = new ResponseEntity<Booking>(booking,HttpStatus.OK);
		}
		else
		{
			System.out.println(" not updated"+roomNumber);
			LOGGER.info("******************** ROOM IS EMPTY, ALLOCATE ROOM ");
			Booking booking1=bookingService.findByUserName(userName);
			result = bookingService.updateRecord(userName,roomNumber);
			System.out.println("updated"+roomNumber);
			
			 
			responseEntity = new ResponseEntity<Booking>(booking1,HttpStatus.OK);
	}
		return responseEntity;
	}
	
	@GetMapping("tosendMail/{userName}")
	public ResponseEntity<String> mailBookingRecord(@PathVariable("userName")String userName)
	{
		//String username = booking.getCustomerUserName();
		ResponseEntity<String> responseEntity = null;
		String from = "Taj-Restaurant";
		 String subject="Room Booking Status";
		  Booking booking1=bookingService.findByUserName(userName);
		  int roomNumber =booking1.getRoomNumber(); 
		  System.out.println("mail "+roomNumber); 
		  String toUserMail =booking1.getEmail();
		  String message = "Congratulations! and Hearty Welcome "+
		  "\n Dear "+booking1.getCustomerUserName()+"\n"
		  +"You have successfully Booked a room in our Hotel with Room no : "
		  +roomNumber+ "With an initial Amount of INR: "+booking1.getRoomPrice();
		  mailApplication.sendMail(from, toUserMail,subject , message);
		  
		  responseEntity = new ResponseEntity<String>(message,HttpStatus.OK);
		 LOGGER.info("Mail Sent Successfully...");
		return responseEntity;
	}
	
	
}
