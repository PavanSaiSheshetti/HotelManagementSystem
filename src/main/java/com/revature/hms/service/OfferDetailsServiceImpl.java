package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.OfferDetails;
import com.revature.hms.repository.OfferDetailsRepository;

@Service
public class OfferDetailsServiceImpl implements  OfferDetailsService {

	@Autowired
	OfferDetailsRepository offerDetailsRepository;
	
	@Override
	public boolean addOffer(OfferDetails offerDetails) {
		
		System.out.println("##Add Offers Called....");
		offerDetailsRepository.save(offerDetails);
		return false;
	}

	@Override
	public boolean updateOffer(OfferDetails offerDetails) {
		
		System.out.println("##Update Offer Called....");
		offerDetailsRepository.save(offerDetails);
		return true;
	}

	@Override
	public boolean deleteOfferById(int offerId) {
		
		System.out.println("##Delete Offer Offers Called....");
		offerDetailsRepository.deleteById(offerId);
		return true;
		
	}

	@Override
	public List<OfferDetails> getAllOffers() {
		
		System.out.println("##Get Offers Called....");
		return (List<OfferDetails>) offerDetailsRepository.findAll();
	}

	@Override
	public OfferDetails getofferById(int offerId) {
		
		System.out.println("##Get Offer By Id Called....");
		Optional<OfferDetails> offers=offerDetailsRepository.findById(offerId);
		OfferDetails offerDetails=offers.get();
		return offerDetails;
	}

	@Override
	public boolean isOfferExits(int offerId) {
		
		Optional<OfferDetails> offers=offerDetailsRepository.findById(offerId);
		return offers.isPresent();
	}

	@Override
	public List<OfferDetails> getOfferByRoomType(String roomType) {
		
		System.out.println("##Get Offers by RoomType Called....");
		return (List<OfferDetails>) offerDetailsRepository.findByRoomType(roomType);
	}

	@Override
	public List<OfferDetails> findByRoomTypeAndRoomSize(String roomType, String roomSize) {
		
		System.out.println("##Get Offers by RoomType and Room Size Called....");
		return (List<OfferDetails>) offerDetailsRepository.findByRoomTypeAndRoomSize(roomType, roomSize);
	}

}
