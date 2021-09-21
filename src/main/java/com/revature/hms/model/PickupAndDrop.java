package com.revature.hms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pickupandropdetails")
public class PickupAndDrop {
	@Id
	private int pickupDropId; 
	private String location;
	private String typeOfTransport;
	private String time;
	private int numberOfPassenger;
}
