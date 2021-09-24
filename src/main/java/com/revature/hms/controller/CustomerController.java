package com.revature.hms.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.model.Bill;
import com.revature.hms.model.Booking;
import com.revature.hms.model.Customer;
import com.revature.hms.model.ForgetPassword;
import com.revature.hms.model.PickupAndDrop;
import com.revature.hms.service.BookingService;
import com.revature.hms.service.CustomerService;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<String> customerSignup(@RequestBody Customer customer) {
		ResponseEntity<String> responseEntity = null;
		String message = null;
		customerService.customerSignup(customer);
		message = "Sign up Successfully ";
		responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	@GetMapping("{customerUserName}/{password}")
	public ResponseEntity<Customer> customerLogin( @PathVariable("customerUserName") String customerUserName,@PathVariable("password") String password) 
	{
		ResponseEntity<Customer> responseEntity = null;
		Customer customer = new Customer();
		String message = null;
		
		if (customerService.isCustomerExists(customerUserName)) {
			customer = customerService.viewProfile(customerUserName);
			System.out.println(customer.getPassword());
			System.out.println(password);
			if(customer.getPassword().equals(password)) {
				message = "Login Successfully ";
				System.out.println( "Login Successfully");
				responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}
			else {
				message = "CustomerUserName/Password is incorrect ";
				System.out.println("incorrect password");
				responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			message = "CustomerUserName/Password is incorrect ";
			System.out.println("passwordcalled");
			responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@GetMapping("{customerUserName}")
	public ResponseEntity<Customer> showProfile(@PathVariable("customerUserName") String customerUserName) {
		ResponseEntity<Customer> responseEntity = null;
		Customer customer = new Customer();
		if (customerService.isCustomerExists(customerUserName)) {
			customer = customerService.viewProfile(customerUserName);
			responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	
	@PutMapping("/updateProfile/{customerUserName}")
	public ResponseEntity<String> editProfile(@RequestBody Customer customer,@PathVariable("customerUserName") String customerUserName) {
		ResponseEntity<String> responseEntity = null;
		String message = null;

		if (customerService.isCustomerExists(customerUserName)) {
			customer.setCustomerUserName(customerUserName);
			customerService.updateCustomer(customer);
			message = "updated successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "Customer ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	
	@PutMapping("/resetPassword/{customerUserName}")
	public ResponseEntity<String> resetPassword(@RequestBody ForgetPassword forget, @PathVariable("customerUserName") String customerUserName) {
		ResponseEntity<String> responseEntity = null;
		String message = null;
		Customer customer = new Customer();
		if (customerService.isCustomerExists(customerUserName)) {
			customer = customerService.getCustomerByUserName(customerUserName);
			customer.setCustomerUserName(customerUserName);
			customer.setPassword(forget.getPassword());
			customerService.updateCustomer(customer);
			message = "updated successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "Patient ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	
	@PostMapping("/addPickAndDrop")
	public ResponseEntity<Integer> addPickAndDrop(@RequestBody PickupAndDrop pickupAndDrop) {
		ResponseEntity<Integer> responseEntity = null;
		int pickupAndDropId;
		pickupAndDropId = customerService.addPickAndDrop(pickupAndDrop);
		responseEntity = new ResponseEntity<Integer>(pickupAndDropId, HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	@PutMapping("/updatePickAndDrop/{pickupAndDropId}")
	public ResponseEntity<String> updatePickAndDrop(@RequestBody PickupAndDrop pickupAndDrop,@PathVariable("pickupAndDropId") int pickupAndDropId) {
		ResponseEntity<String> responseEntity = null;
		String message = null;

		if (customerService.isPickAndDropExists(pickupAndDropId)) {
			pickupAndDrop.setPickupDropId(pickupAndDropId);
			customerService.updatePickAndDrop(pickupAndDrop);
			message = "updated successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "PickupAndDrop ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	@GetMapping("/getPickAndDrop/{pickupAndDropId}")
	public ResponseEntity<PickupAndDrop> getPickAndDrop(@PathVariable("pickupAndDropId") int pickupAndDropId) {
		ResponseEntity<PickupAndDrop> responseEntity = null;
		PickupAndDrop pickupAndDrop = new PickupAndDrop();
		String message = null;
		
		if (customerService.isPickAndDropExists(pickupAndDropId)) {
			pickupAndDrop = customerService.getPickupAndDrop(pickupAndDropId); 
			message = "updated successfully";
			responseEntity = new ResponseEntity<PickupAndDrop>(pickupAndDrop, HttpStatus.OK);
		} else {
			message = "PickupAndDrop ID is not Exist";
			responseEntity = new ResponseEntity<PickupAndDrop>(pickupAndDrop, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/cancelPickAndDrop/{pickupAndDropId}")
	public ResponseEntity<String> cancelPickAndDrop(@PathVariable("pickupAndDropId") int pickupAndDropId) {
		ResponseEntity<String> responseEntity = null;
		String message = null;

		if (customerService.isPickAndDropExists(pickupAndDropId)) {
			customerService.cancelPickAndDrop(pickupAndDropId);
			message = "Deleted successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "PickupAndDrop ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@GetMapping("/viewBill/{customerUserName}")
	public ResponseEntity<List<Bill>> viewBill(@PathVariable("customerUserName") String customerUserName) {
		ResponseEntity<List<Bill>> responseEntity = null;
		List<Bill> bills = new ArrayList<Bill>();
		if (customerUserName== null) 
		{
			bills = customerService.viewBill(customerUserName);
		} else {
			bills = (List<Bill>) customerService.viewBill(customerUserName);
			}
		if(bills.size()==0)
		{
			responseEntity = new ResponseEntity<List<Bill>>(bills, HttpStatus.NO_CONTENT);
		}else {
			responseEntity = new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
			
		}
		return responseEntity;
	}
	
	
	@PostMapping("/bookingForm")
	public ResponseEntity<Integer> bookingForm(@RequestBody Booking booking) {
		ResponseEntity<Integer> responseEntity = null;
		int bookingId;
		bookingId = customerService.bookingForm(booking);
		responseEntity = new ResponseEntity<Integer>(bookingId, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/viewBookingHistory/{customerUserName}")
	public ResponseEntity<List<Booking>> showBookingHistory(@PathVariable("customerUserName") String customerUserName) {
		ResponseEntity<List<Booking>> responseEntity = null;
		List<Booking> bookings = new ArrayList<Booking>();
		if (customerService.isCustomerExists(customerUserName)) {
			bookings = customerService.viewBookingHistory(customerUserName);
			responseEntity = new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<List<Booking>>(bookings, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	@GetMapping("/viewBookingByRoomId/{roomId}")
	public ResponseEntity<Booking> showBookingByRoomId(@PathVariable("roomId") int roomId) {
		ResponseEntity<Booking> responseEntity = null;
		Booking booking = new Booking();
		if (customerService.isBookingExists(roomId)) {
//			bookings = customerService.viewBookingHistory(customerUserName);
			booking =  customerService.viewBookingById(roomId);
			responseEntity = new ResponseEntity<Booking>(booking, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Booking>(booking, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	@PutMapping("/updateBooking/{bookingId}")
	public ResponseEntity<String> updateBooking(@RequestBody Booking booking,@PathVariable("bookingId") int bookingId) {
		ResponseEntity<String> responseEntity = null;
		String message = null;

		if (customerService.isBookingExists(bookingId)) {
			booking.setBookingId(bookingId);
			customerService.updateBooking(booking);
			message = "updated successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "PickupAndDrop ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	@DeleteMapping("/cancelBooking/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") int bookingId) {
		ResponseEntity<String> responseEntity = null;
		String message = null;
		
		if (customerService.isBookingExists(bookingId)) {
			customerService.cancelBooking(bookingId);
			message = "Deleted successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
		} else {
			message = "Booking ID is not Exist";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PutMapping("updatePickupDropStatus/{userName}/{status}")
	public ResponseEntity<String> updatePickupDropStatus(@PathVariable String userName, @PathVariable String status) {

		ResponseEntity<String> responseEntity = null;
		customerService.updatePickupDropStatus(status, userName);
		responseEntity = new ResponseEntity<String>("Updated successfully", HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	@PutMapping("updateCancellationStatus/{userName}/{status}")
	public ResponseEntity<String> updateCancellationStatus(@PathVariable String userName, @PathVariable String status) {

		ResponseEntity<String> responseEntity = null;
		bookingService.updateCancellationStatus(status, userName);
		responseEntity = new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
		return responseEntity;
	}	

	
}
