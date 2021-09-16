package com.revature.hms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.hms.model.Room;


@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

}
