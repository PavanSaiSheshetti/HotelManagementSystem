package com.revature.hms.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.comparator.IdComparator;
import com.revature.hms.model.BookingHistory;
import com.revature.hms.service.AdminViewBookingHistoryService;


@RestController
@RequestMapping("viewbookinghistory")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminViewBookingHistoryController {
	
	@Autowired
	AdminViewBookingHistoryService adminViewBookingHistoryService;
	
	@Autowired
	List<BookingHistory> allBookingDetails;
	
	@PostMapping
	public ResponseEntity<String> addBooking(@RequestBody BookingHistory bookingHistory){
		ResponseEntity<String> responseEntity = null;
		String message = null;
	
		System.out.println(bookingHistory);
		adminViewBookingHistoryService.addBooking(bookingHistory);
		int bookingId = bookingHistory.getBookingId();
		message = "Booking with booking id : "+ bookingId + " saved successfully";
		responseEntity = new ResponseEntity<String>(message,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<List<BookingHistory>> getBookingDetails(){
		ResponseEntity<List<BookingHistory>> responseEntity = null;
		allBookingDetails = adminViewBookingHistoryService.getAllBookings();
		if(allBookingDetails.size()==0) {
			System.out.println("No bookings are available");
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.NO_CONTENT);			
		}
		else {
			System.out.println(allBookingDetails);
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
		}
		return responseEntity;
		
	}
	
	@GetMapping("{customerId}")
	public ResponseEntity<BookingHistory> getBookingDetailsByCustomerId(@PathVariable int customerId){
		ResponseEntity<BookingHistory> responseEntity = null;
		BookingHistory allBookingDetails = new BookingHistory();
		if(adminViewBookingHistoryService.isBookingExists(customerId)) {
			allBookingDetails = adminViewBookingHistoryService.getBookingById(customerId);
			System.out.println(allBookingDetails);
			responseEntity = new ResponseEntity<BookingHistory>(allBookingDetails,HttpStatus.OK);
		}
		else {
			responseEntity = new ResponseEntity<BookingHistory>(allBookingDetails,HttpStatus.OK);
	
		}
		return responseEntity;
		
	}
	
	@GetMapping("customer/{customerUserName}")
	public ResponseEntity<List<BookingHistory>> getBookingDetailsByCustomerName(@PathVariable String customerUserName){
		ResponseEntity<List<BookingHistory>> responseEntity = null;
		allBookingDetails = adminViewBookingHistoryService.getBookingByCustomerUserName(customerUserName);
		System.out.println(allBookingDetails);
		responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("roomDetails/type/{roomType}")
	public ResponseEntity<List<BookingHistory>> getBookingDetailsByRoomType(@PathVariable String roomType){
		ResponseEntity<List<BookingHistory>> responseEntity = null;
		allBookingDetails = adminViewBookingHistoryService.getBookingByRoomType(roomType);
		System.out.println(allBookingDetails);
		responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("roomDetails/number/{roomNumber}")
	public ResponseEntity<List<BookingHistory>> getBookingDetailsByRoomNumber(@PathVariable int  roomNumber){
		ResponseEntity<List<BookingHistory>> responseEntity = null;
		allBookingDetails = adminViewBookingHistoryService.getBookingByRoomNumber(roomNumber);
		System.out.println(allBookingDetails);
		responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("sorting/{sortingParameter}")
	public ResponseEntity<List<BookingHistory>> getBookingDetailsBySorting(@PathVariable("sortingParameter") String  sortingParameter){
		ResponseEntity<List<BookingHistory>> responseEntity = null;

		System.out.println(sortingParameter);
		System.out.println("Before sorting - "+this.allBookingDetails);
		switch(sortingParameter) {
		case "id":
			Collections.sort(allBookingDetails, new IdComparator());
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
			return responseEntity;
		case "name":
			Collections.sort(allBookingDetails, new IdComparator(){
				@Override
				public int compare(BookingHistory o1,BookingHistory o2) {
					if(o1.getCustomerUserName().compareTo(o2.getCustomerUserName())>0) {
						return 1;
					}
					else {
						return -1;
					}
				}
			});
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
			return responseEntity;
		case "checkInDate" :
			Collections.sort(allBookingDetails, new IdComparator(){
				@Override
				public int compare(BookingHistory o1,BookingHistory o2) {
					if(o1.getCheckInDate().compareTo(o2.getCheckInDate())>0) {
						return 1;
					}
					else {
						return -1;
					}
				}
			});
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
			return responseEntity;
		case "checkOutDate" :
			Collections.sort(allBookingDetails, new IdComparator(){
				@Override
				public int compare(BookingHistory o1,BookingHistory o2) {
					if(o1.getCheckOutDate().compareTo(o2.getCheckOutDate())>0) {
						return 1;
					}
					else {
						return -1;
					}
				}
			});
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
			return responseEntity;
		case "roomNumber" :
			Collections.sort(allBookingDetails, new IdComparator(){
				@Override
				public int compare(BookingHistory o1,BookingHistory o2) {
					if(o1.getRoomNumber()>o2.getRoomNumber()) {
						return 1;
					}
					else {
						return -1;
					}
				}
			});
			responseEntity = new ResponseEntity<List<BookingHistory>>(allBookingDetails,HttpStatus.OK);
			return responseEntity;
		default : 
			return responseEntity;
	}
	}
	
	
	
		

}
