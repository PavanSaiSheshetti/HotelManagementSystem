package com.revature.training.service;

import java.util.List;

import com.revature.training.model.BookingHistory;

public interface AdminViewBookingHistoryService {
	
	List<BookingHistory> getAllBookings();
	
	BookingHistory getBookingById(int bookingId);
	
	List<BookingHistory> getBookingByCustomerUserName(String customerUserName);
	
	List<BookingHistory> getBookingByRoomType(String roomType);
	
	List<BookingHistory> getBookingByRoomNumber(int roomNumber);
	
	boolean addBooking(BookingHistory bookingHistory);
	
	boolean isBookingExists(int bookingId);
	
	

}
