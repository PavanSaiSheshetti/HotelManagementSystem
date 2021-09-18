package com.revature.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.BookingHistory;

@EnableJpaRepositories
public interface AdminViewBookingHistoryRepository extends CrudRepository<BookingHistory, Integer> {

	List<BookingHistory> findAllByCustomerUserNameContains(String customerUserName);

	List<BookingHistory> findAllByRoomType(String roomType);

	List<BookingHistory> findAllByRoomNumber(int roomNumber);
	


}
