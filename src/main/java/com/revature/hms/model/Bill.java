package com.revature.hms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "billdetails")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int billNo; 
	private int customerUserName;
	private int roomBill;
	private int breakfastBill; 
	private int drinksBill; 
	private int foodBill;
	private int pickupAndDropBill;
	private int totalBill;
}
