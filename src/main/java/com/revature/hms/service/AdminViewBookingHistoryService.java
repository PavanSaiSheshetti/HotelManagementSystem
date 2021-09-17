package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import com.revature.hms.model.BookingHistory;

public interface AdminViewBookingHistoryService {
	
	List<BookingHistory> getAllBookings();
	
	BookingHistory getBookingById(int bookingId);
	
	List<BookingHistory> getBookingByCustomerUserName(String customerUserName);
	
	List<BookingHistory> getBookingByRoomType(String roomType);
	
	List<BookingHistory> getBookingByRoomNumber(int roomNumber);
	
	boolean addBooking(BookingHistory bookingHistory);
	
	boolean isBookingExists(int bookingId);
	
	

}
