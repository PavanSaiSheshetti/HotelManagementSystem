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

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="booking_history")	
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BookingHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int bookingId;
	
	private String customerUserName;
	
	@Column(nullable= false)
	private String customerName;
	
	@Column(nullable=false, unique=true)
	private String customerMobileno;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column
	private String idProof;
	
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
	
//	@Basic
//	@Temporal(TemporalType.DATE)
//	@Column(nullable=false)
	private String checkInDate;
	
//	@Basic
//	@Temporal(TemporalType.DATE)
//	@Column(nullable=false)
	private String checkOutDate;
	
	@Column
	private int roomNumber;
	
	@Column
	private String pickupAndDrop;
	
	@Column
	private String cancellation="False";
	
	@Column(nullable=false)
	private String bookingStatus="Pending";
	
	@Column(nullable=false)
	private int roomPrice;		
	
	@Column
	private String specialization;
	
}

