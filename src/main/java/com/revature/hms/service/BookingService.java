package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.Booking;
import com.revature.hms.model.BookingHistory;

public interface BookingService {

	public List<Booking> viewBookingRecords();

	public List<Booking> viewCancellations(String cancellation);

	public List<Booking> viewBookedRooms(int roomNumber);

	public boolean isRoomNumberExists(int roomNumber);

	public boolean deleteRecord(String userName);

	public boolean updateRecord(String userName,int roomNumber);

	public boolean deleteByUserName(String userName);

	public void updateStatus(String userName, String status);

	public Booking findByUserName(String userName);
	
	public List<BookingHistory> getBookingsHistory();
}
