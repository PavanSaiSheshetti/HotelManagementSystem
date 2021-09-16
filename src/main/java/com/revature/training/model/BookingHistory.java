package com.revature.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="booking_history")
public class BookingHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	@Column
	private String customerUserName;
	@Column
	private String roomType;
	@Column
	private int numberOfRooms;
	@Column
	private int roomNumber;
	@Column
	private String roomSize;
	@Column
	private String breakfast;
	@Column
	private String drinks;
	@Column
	private Date checkInDate;
	@Column
	private Date checkOutDate;
	@Column
	private String pickupAndDrop;
	@Column
	private String cancellation;
	@Column
	private String specialization;
	
	
	

}
