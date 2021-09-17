package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.OfferDetails;

public interface OfferDetailsService {

	public boolean addOffer(OfferDetails offerDetails);
	public boolean updateOffer(OfferDetails offerDetails);
	public boolean deleteOfferById(int offerId);
	public List<OfferDetails> getAllOffers();
	public OfferDetails getofferById(int offerId);
	public boolean isOfferExits(int offerId);
	public List<OfferDetails> getOfferByRoomType(String roomType);
	public List<OfferDetails> findByRoomTypeAndRoomSize(String roomType,String roomSize);
	
}
