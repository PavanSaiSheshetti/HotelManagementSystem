package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.Bill;
import com.revature.hms.model.Booking;
import com.revature.hms.model.Customer;
import com.revature.hms.model.PickupAndDrop;

public interface CustomerService {

	public boolean customerSignup(Customer customer);
	public Customer customerLogin(String customerUserName, String password);
	public boolean isCustomerExists(String customerUserName);
	public Customer viewProfile(String customerUserName);
	public boolean updateCustomer(Customer customer);
	public Customer getCustomerByUserName(String customerUserName);
	
	public int bookingForm(Booking booking);
	public List<Booking> viewBookingHistory(String customerUserName);
	public Booking viewBookingById(int booingId);
	public boolean updateBooking(Booking booking);
	public boolean cancelBooking(int bookingId);
	public boolean isBookingExists(int bookingId);
	
	public int addPickAndDrop(PickupAndDrop pickupAndDrop);
	public boolean updatePickAndDrop(PickupAndDrop pickupAndDrop);
	public boolean cancelPickAndDrop(int pickupAndDropId);
	public boolean isPickAndDropExists(int pickupAndDropId);
	public PickupAndDrop getPickupAndDrop(int pickupAndDropId);
	
	public List<Bill> viewBill(String customerUserName);
//	
//	public int getCustomerId(String customerUserName);
}
