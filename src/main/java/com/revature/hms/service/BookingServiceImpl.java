package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Booking;
import com.revature.hms.model.BookingHistory;
import com.revature.hms.repository.BookingHistoryRepository;
import com.revature.hms.repository.BookingRepository;


@Service
public class BookingServiceImpl implements BookingService {

	Logger LOGGER=LoggerFactory.getLogger(BookingServiceImpl.class);
	Booking booking;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	BookingHistoryRepository bookingsHistoryRepository;

	@Override
	public List<Booking> viewBookingRecords() {
		LOGGER.info("--------------------- BOOKING RECORDS METHOD CALLED");
		return ( List<Booking>)bookingRepository.findAll();
		
	}

	@Override
	public List<Booking> viewCancellations(String cancellation) {
		LOGGER.info("--------------------- CANCELLED BOOKING RECORDS METHOD CALLED");
		return bookingRepository.findByCancellation(cancellation);
	}
	

	@Override
	public boolean deleteRecord(String userName) {
		LOGGER.info("--------------------- RECORD DELETED METHOD CALLED  ");
		bookingRepository.deleteByCustomerUserName(userName);
		return true;
	}

	@Override
	public boolean updateRecord(Booking booking) {
		LOGGER.info("--------------------- RECORD UPDATED METHOD CALLED ");
		bookingRepository.save(booking);
		return true;
	}

	@Override
	public boolean isRoomNumberExists(int roomNumber) {
		LOGGER.info("--------------------- BOOKING RECORD EXISTS METHOD CALLED ");
		Optional<Booking> bookingRoom = bookingRepository.findByRoomNumber(roomNumber);
		return bookingRoom.isPresent();
	}

	@Override
	public List<Booking> viewBookedRooms(int roomNumber) {
		LOGGER.info("--------------------- ROOMS OF BOOKED RECORDS METHOD CALLED");
		return bookingRepository.findByRoomNumberGreaterThan(roomNumber);
	}


	@Override
	public boolean deleteByUserName(String userName) {
		LOGGER.info("--------------------- DELETE BY USERNAME METHOD CALLED");
		bookingRepository.deleteByCustomerUserName(userName);
		return true;
	}

	@Override
	public Booking findByUserName(String userName) {
		LOGGER.info("--------------------- FIND BY USERNAME METHOD CALLED");
	    Booking booking=bookingRepository.findByCustomerUserName(userName);
		return booking;
	}
	
	@Transactional
	@Override
	public void updateStatus(String userName, String status) {
		// TODO Auto-generated method stub
		LOGGER.info("------------------UPDATE STATUS is called");
		bookingRepository.updateStatus(userName, status);
	}

	@Override
	public List<BookingHistory> getBookingsHistory() {
		// TODO Auto-generated method stub
		LOGGER.info("Bookings history called");
		List<BookingHistory> bookingsHistoryList = bookingsHistoryRepository.findAll();
		return bookingsHistoryList;
	}
}
