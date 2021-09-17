package com.revature.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.OfferDetails;

public interface OfferDetailsRepository extends CrudRepository<OfferDetails,Integer>{

	public List<OfferDetails> findByRoomType(String roomType);
	public List<OfferDetails> findByRoomTypeAndRoomSize(String roomType,String roomSize);
	
}
