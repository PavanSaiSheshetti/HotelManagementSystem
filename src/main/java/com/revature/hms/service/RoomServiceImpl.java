package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Room;
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


}
