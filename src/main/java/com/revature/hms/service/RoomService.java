package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.Room;


public interface RoomService {
	
	public boolean addRoom(Room room);
	public boolean deleteRoom(int roomId);
	public boolean updateRoom(Room room);
	public Room getRoomById(int roomId);	
	public List<Room> getAllRooms();
	public boolean isRoomExists(int roomId);
	public List<Room> roomByStatus(boolean roomStatus);
	public int updateStatus(int roomId,boolean roomStatus);
	public Room findByRoomTypeAndRoomSize(String roomType,String roomSize);
	public boolean insertRoomByCustomer(String roomType,String roomSize,int price);

}
