package com.revature.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Booking;
import com.revature.hms.model.BookingHistory;
import com.revature.hms.repository.BookingHistoryRepository;



@Service

public class BookingHistoryServiceImpl implements BookingHistoryService{
	@Autowired
	private BookingHistoryRepository bookingHistoryRepository;
	
	@Autowired
	private BookingService bookingService;

	@Override
	public boolean addToHistory(String userName) {
		Booking customerBooking = new Booking();
		customerBooking =  bookingService.findByUserName(userName);
		BookingHistory bookingHistory=new BookingHistory(customerBooking.getBookingId(),customerBooking.getCustomerUserName(),customerBooking.getCustomerName(), customerBooking.getCustomerMobileno(), customerBooking.getEmail(),customerBooking.getIdProof(),customerBooking.getRoomType(),customerBooking.getRoomSize(),customerBooking.getBreakfast(),customerBooking.getDrinks(),customerBooking.getNumberOfRooms(),customerBooking.getNumberOfMembers(), customerBooking.getCheckInDate(), customerBooking.getCheckOutDate(),customerBooking.getRoomNumber(),customerBooking.getPickupAndDrop(),customerBooking.getCancellation(), customerBooking.getBookingStatus(), customerBooking.getRoomPrice(),customerBooking.getSpecialization());
		bookingHistoryRepository.save(bookingHistory);
		return true;
	}

	@Override
	public boolean deleteHistory(int bookingId) {
		// TODO Auto-generated method stub
		bookingHistoryRepository.deleteById(bookingId);
		return true;
	}

}
