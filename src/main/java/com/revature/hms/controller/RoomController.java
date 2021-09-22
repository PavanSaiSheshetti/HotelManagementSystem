package com.revature.hms.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.model.OfferDetails;
import com.revature.hms.model.Room;
import com.revature.hms.service.RoomService;



@RestController

@RequestMapping("room")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

	@Autowired
	RoomService roomService;

	
	// get room by id

	@GetMapping("/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable("roomId") int roomId) {

		Room room = new Room();
		if (roomService.isRoomExists(roomId)) {
			room = roomService.getRoomById(roomId);
			return new ResponseEntity<>(room, HttpStatus.OK);
		} else
			return new ResponseEntity<>(room, HttpStatus.NO_CONTENT);

		

	}

	// insert a room

	@PostMapping
	public ResponseEntity<String> addRoom(@RequestBody Room room) {

		int roomId = room.getRoomId();

		if (roomService.isRoomExists(roomId))

			return new ResponseEntity<>(HttpStatus.CONFLICT);

		else {
			roomService.addRoom(room);

			return new ResponseEntity<>(HttpStatus.OK);
		}

		
	}

	// update a room

	@PutMapping
	public ResponseEntity<String> updateRoom(@RequestBody Room room) {

		int roomId = room.getRoomId();

		if (roomService.isRoomExists(roomId)) {
			roomService.updateRoom(room);

			return  new ResponseEntity<>(HttpStatus.OK);
		} else
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

		
	}

	// delete a room

	@DeleteMapping("/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable("roomId") int roomId) {

		if (roomService.isRoomExists(roomId)) {
			roomService.deleteRoom(roomId);

			return new ResponseEntity<>(HttpStatus.OK);
		} else
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);

		
	}

	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms() {

		List<Room> roomList = roomService.getAllRooms();
		if(roomList.size()==0)
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
	}
	
	@PutMapping("/updateRoomByStatus/{roomId}/{roomStatus}")
	public int updateRoomByStatus(@PathVariable("roomStatus")boolean roomStatus,@PathVariable("roomId")int roomId)
	{
		return roomService.updateStatus(roomId, roomStatus);
		
	}
	
	@GetMapping("getRoom/{roomType}/{roomSize}")
	public ResponseEntity<Room>  getRoomByRoomTypeAndRoomSize(@PathVariable("roomType") String roomType,@PathVariable("roomSize") String roomSize) {
	
		Room room = new Room();
		room=roomService.findByRoomTypeAndRoomSize(roomType,roomSize);
		System.out.println(room);
		if(room == null) {
		return new ResponseEntity<Room> (room,HttpStatus.CONFLICT);
		}
		else {
			
			return new ResponseEntity<Room> (room,HttpStatus.OK);
		}
			
	}
	
	@PostMapping("insertRoom/{roomType}/{roomSize}")
	public ResponseEntity<Boolean>  insertRoomByCustomer(@PathVariable("roomType") String roomType,@PathVariable("roomSize") String roomSize) {
	
		boolean result = roomService.insertRoomByCustomer(roomType, roomSize, 0);
		if(result) {
		return new ResponseEntity<Boolean> (result,HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<Boolean> (result,HttpStatus.CONFLICT);
		}
			
	}
	
	
	
	@GetMapping("/roomByStatus/{roomStatus}")
	public ResponseEntity<List<Room>> getAllRoomsByStatus(@PathVariable("roomStatus")boolean roomStatus) {

		List<Room> roomList = roomService.roomByStatus(roomStatus);
		if(roomList.size()==0)
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(roomList, HttpStatus.OK);
	}

	
	

}

