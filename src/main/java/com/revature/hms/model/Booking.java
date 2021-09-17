package com.revature.hms.model;


import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="bookings")	
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	
	@Id
	private String userName;
	
	@Column(nullable= false)
	private String customerName;
	
	@Column(nullable=false, unique=true)
	private String customerMobileno;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String roomType;
	
	@Column
	private String roomSize;
	
	@Column
	private String breakfast;
	@Column
	private String drinks;

	
	@Column(nullable=false)
	private int numberOfRooms;
	
	@Column(nullable=false)
	private int numberOfMembers;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Calendar customerCheckIn;
	
	@Basic
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Calendar customerCheckOut;
	
	@Column
	private int roomNumber;
	
	@Column
	private String pickupAndDrop;
	
	@Column
	private String cancellation;
	
	@Column(nullable=false)
	private String bookingStatus;
	
	@Column(nullable=false)
	private int amountPaid;		
	
	@Column
	private String specialization;
	
}

