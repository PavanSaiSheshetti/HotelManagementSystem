package com.revature.hms.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.revature.hms.model.Booking;




public interface BookingRepository extends CrudRepository<Booking,Integer>{
	
	@Transactional
	@Query(value = "Select b FROM Booking b where b.customerUserName =:customerUserName ")
	public List<Booking> findByUserName (@Param("customerUserName") String customerUserName);

	public List<Booking>findByCancellation(String cancellation);
	public List<Booking>findByRoomNumberGreaterThan(int roomNumber);
	public Booking findByRoomNumber(int roomNumber);
	
	public Booking findByCustomerUserName(String userName);
		
	@Transactional
	public String deleteByCustomerUserName(String userName);
	public List<Booking>findByRoomNumberIs(int roomNumber);
	@Modifying
	@Query(value = "UPDATE FROM Booking set bookingStatus= ?2 where customerUserName = ?1")
	public void updateStatus(String userName,String status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE FROM Booking set pickupAndDrop= ?2 where customerUserName = ?1")
	public void updatePickupDropStatus(String userName,String status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Booking b set b.roomNumber=:roomNumber where b.customerUserName =:customerUserName")
	public int updateRoomNumber(@Param("customerUserName") String customerUserName, @Param("roomNumber") int roomNumber);
	
	public Booking findByBookingId(int bookingId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE FROM Booking set room_price= ?1 where customerUserName = ?2")
	public void updatePrice(int price,String userName);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE FROM Booking set cancellation= ?1 where customerUserName = ?2")
	public void updateCancellation(String status,String userName);

}
