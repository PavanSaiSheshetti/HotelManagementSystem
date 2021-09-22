package com.revature.hms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.hms.model.OfferDetails;
import com.revature.hms.model.Room;


@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
	public List<Room> findByRoomStatus(boolean roomStatus);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Room r set r.roomStatus=:roomStatus where r.roomId=:roomId ")
	public int updateStatusByRoom(@Param("roomStatus")boolean roomStatus,@Param("roomId")int roomId);
	
	public Room findByRoomTypeAndRoomSize(String roomType,String roomSize);
	

}
