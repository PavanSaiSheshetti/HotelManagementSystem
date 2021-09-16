package com.revature.hms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component

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
	
	public Room() {
		
	}

	public Room( int roomId, int floorNumber,String roomSize, String roomType, int roomPrice, String roomView) {
		super();
		this.floorNumber = floorNumber;
		this.roomId = roomId;
		this.roomSize = roomSize;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.roomView = roomView;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomView() {
		return roomView;
	}

	public void setRoomView(String roomView) {
		this.roomView = roomView;
	}

	@Override
	public String toString() {
		return "Room [floorNumber=" + floorNumber + ", roomNumber=" + roomId + ", roomSize=" + roomSize
				+ ", roomType=" + roomType + ", roomPrice=" + roomPrice + ", roomView=" + roomView + "]";
	}

	
}
