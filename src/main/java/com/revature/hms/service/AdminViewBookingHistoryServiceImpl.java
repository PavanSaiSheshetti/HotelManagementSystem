package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.BookingHistory;
import com.revature.hms.repository.AdminViewBookingHistoryRepository;

@Service
public class AdminViewBookingHistoryServiceImpl implements AdminViewBookingHistoryService {
	
	@Autowired
	AdminViewBookingHistoryRepository adminViewBookingHistoryRepository;
	@Override
	public List<BookingHistory> getAllBookings() {
		List<BookingHistory> bookingDetails = (List<BookingHistory>) adminViewBookingHistoryRepository.findAll();
		return bookingDetails;
	}
	
	@Override
	public BookingHistory getBookingById(int bookingId) {
		Optional<BookingHistory> bookings = adminViewBookingHistoryRepository.findById(bookingId);
		BookingHistory bookingsHistory = bookings.get();
		return bookingsHistory;
	}

	@Override
	public List<BookingHistory> getBookingByCustomerUserName(String customerUserName) {
		return adminViewBookingHistoryRepository.findAllByCustomerUserNameContains(customerUserName);
	}

	@Override
	public List<BookingHistory> getBookingByRoomType(String roomType) {
		return adminViewBookingHistoryRepository.findAllByRoomType(roomType);
	}

	@Override
	public List<BookingHistory> getBookingByRoomNumber(int roomNumber) {
		return adminViewBookingHistoryRepository.findAllByRoomNumber(roomNumber);
	}

	@Override
	public boolean addBooking(BookingHistory bookingHistory) {
		adminViewBookingHistoryRepository.save(bookingHistory);
		return true;
	}

	@Override
	public boolean isBookingExists(int bookingId) {
		Optional<BookingHistory> booking = adminViewBookingHistoryRepository.findById(bookingId);
		return booking.isPresent();
	}

}
