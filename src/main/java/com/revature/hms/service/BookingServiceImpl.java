package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Booking;
import com.revature.hms.repository.BookingRepository;


@Service
public class BookingServiceImpl implements BookingService {

	Logger LOGGER=LoggerFactory.getLogger(BookingServiceImpl.class);
	Booking booking;
	
	@Autowired
	BookingRepository bookingRepository;

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
		bookingRepository.deleteById(userName);
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
		bookingRepository.deleteByUserName(userName);
		return true;
	}

	@Override
	public Booking findByUserName(String userName) {
		LOGGER.info("--------------------- FIND BY USERNAME METHOD CALLED");
	    Booking booking=bookingRepository.findByUserName(userName);
		return booking;
	}
}
