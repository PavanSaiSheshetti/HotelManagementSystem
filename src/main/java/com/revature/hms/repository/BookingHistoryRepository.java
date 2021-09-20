package com.revature.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hms.model.BookingHistory;


public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Integer>{

}
