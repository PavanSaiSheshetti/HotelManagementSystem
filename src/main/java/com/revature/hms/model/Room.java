package com.revature.hms.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name="roomstable")
public class Room {
	@Id
	
	private int roomId;
	
	@Column(nullable=false)
	private int floorNumber;
	
	@Column(nullable=false)
	private String roomSize;
	
	@Column(nullable=false)
	private String roomType;
	
	@Column(nullable=false)
	private int roomPrice;
	
	@Column(nullable=false)
	private String roomView;
	
	@Column
	private boolean roomStatus=false;
	
}

