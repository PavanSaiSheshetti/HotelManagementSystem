package com.revature.hms.service;

public interface BookingHistoryService {
	
	public boolean addToHistory(String userName);
	
	public boolean deleteHistory(int bookingId);

}
