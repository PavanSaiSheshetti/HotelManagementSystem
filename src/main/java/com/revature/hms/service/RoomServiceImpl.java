package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Room;
import com.revature.hms.repository.BookingRepository;
import com.revature.hms.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	
	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public boolean addRoom(Room room) {
		return roomRepository.save(room) != null;
	}

	@Override
	public boolean deleteRoom(int roomId) {
		roomRepository.deleteById(roomId);
		return true;
	}

	@Override
	public boolean updateRoom(Room room) {
		return roomRepository.save(room) != null;
		
	}

	@Override
	public Room getRoomById(int roomId) {
		Optional<Room> roomData = roomRepository.findById(roomId);
		 return roomData.get();
		
	}

	@Override
	public List<Room> getAllRooms() {
		return (List<Room>) roomRepository.findAll();
		
	}

	@Override
	public boolean isRoomExists(int roomId) {
		Optional<Room> roomData = roomRepository.findById(roomId);
		return roomData.isPresent();
	}

	@Override
	public List<Room> roomByStatus(boolean roomStatus) {
	
		return roomRepository.findByRoomStatus(roomStatus);
	}

	@Override
	public int updateStatus(int roomId, boolean roomStatus) {
		
		return roomRepository.updateStatusByRoom(roomStatus, roomId);
	}

	@Override
	public Room findByRoomTypeAndRoomSize(String roomType, String roomSize) {
		// TODO Auto-generated method stub
		return roomRepository.findByRoomTypeAndRoomSize(roomType, roomSize);
	}

	@Override
	public boolean insertRoomByCustomer(String roomType, String roomSize, int price) {
		// TODO Auto-generated method stub
		int roomId = (int)(100 + Math.random() * 900);
		Room room = new Room(roomId, 9, roomSize, roomType, 25000, "Balcony View", false);
		roomRepository.save(room);
		return true;
		
	}


}
