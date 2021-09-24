package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Bill;
import com.revature.hms.model.Booking;
import com.revature.hms.model.Customer;
import com.revature.hms.model.PickupAndDrop;
import com.revature.hms.repository.BillRepository;
import com.revature.hms.repository.BookingRepository;
import com.revature.hms.repository.CustomerRepository;
import com.revature.hms.repository.PickupAndDropRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PickupAndDropRepository pickupAndDropRepository;
	
	@Autowired
	BillRepository billRepository;

	@Override
	public boolean customerSignup(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("Customer Signup Called");
		customerRepository.save(customer);
		return true;
	}

	@Override
	public Customer customerLogin(String customerUserName, String password) {
		System.out.println("Patient Login called");
		return customerRepository.findByCustomerUserNameAndPassword(customerUserName, password);
	}
	
	@Override
	public boolean isCustomerExists(String customerUserName) {
		Customer customer=customerRepository.findByCustomerUserName(customerUserName);
		if(customer!=null)
			return true;
		else
			return false;
	}

	@Override
	public Customer viewProfile(String customerUserName) {
		Customer customer = customerRepository.findByCustomerUserName(customerUserName);
		return customer;
	}
	
	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return true;
	}

	@Override
	public Customer getCustomerByUserName(String customerUserName) {	
		Customer customer = customerRepository.findByCustomerUserName(customerUserName);
		return customer;
	}

	@Override
	public int bookingForm(Booking booking) {
		bookingRepository.save(booking);
		int bookingId = booking.getBookingId();
		return bookingId;
	}

	@Override
	public List<Booking> viewBookingHistory(String customerUserName) {
		return (List<Booking>) bookingRepository.findByUserName(customerUserName);
	}

	@Override
	public boolean updateBooking(Booking booking) {
		bookingRepository.save(booking);
		return true;
	}

	@Override
	public boolean cancelBooking(int bookingId) {
		bookingRepository.deleteById(bookingId);
		return false;
	}


	@Override
	public int addPickAndDrop(PickupAndDrop pickupAndDrop) {
		pickupAndDropRepository.save(pickupAndDrop);
		int pickupAndDropId = pickupAndDrop.getPickupDropId();
		return pickupAndDropId;
	}

	@Override
	public boolean updatePickAndDrop(PickupAndDrop pickupAndDrop) {
		pickupAndDropRepository.save(pickupAndDrop);
		return true;
	}

	@Override
	public boolean cancelPickAndDrop(int pickupAndDropId) {
		pickupAndDropRepository.deleteById(pickupAndDropId);
		return true;
	}

	@Override
	public List<Bill> viewBill(String customerUserName) {
		// TODO Auto-generated method stub
		return (List<Bill>) billRepository.findByCustomerUserName(customerUserName);
	}
//
//	@Override
//	public int getCustomerId(String customerUserName) {
//		Customer customerData = customerRepository.findByCustomerUserName(customerUserName);
//		int customerId = customerData.getCustomerId();
//		System.out.println(customerId);
//		return customerId;
//	}

	@Override
	public boolean isPickAndDropExists(int pickupAndDropId) {
		Optional<PickupAndDrop> pickupAndDrop=pickupAndDropRepository.findById(pickupAndDropId);
		if(pickupAndDrop!=null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isBookingExists(int bookingId) {
		Optional<Booking> booking=bookingRepository.findById(bookingId);
		if(booking!=null)
			return true;
		else
			return false;
	}

	@Override
	public Booking viewBookingById(int bookingId) {
		Booking booking = bookingRepository.findByBookingId(bookingId);
		return booking;
	}

	@Override
	public PickupAndDrop getPickupAndDrop(int pickupAndDropId) {
		PickupAndDrop pickupAndDrop = pickupAndDropRepository.findByPickupDropId(pickupAndDropId);
		return pickupAndDrop;
//		return null;
	}

	@Override
	public boolean updatePickupDropStatus(String status,String userName) {
		// TODO Auto-generated method stub
		bookingRepository.updatePickupDropStatus(userName, status);
		return true;
	}

	

}
