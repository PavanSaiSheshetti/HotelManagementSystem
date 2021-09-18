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
import com.revature.hms.service.OfferDetailsService;

@RestController
@RequestMapping("hoteloffer")
@CrossOrigin(origins = "http://localhost:4200/")
public class OfferDetailsController {

	@Autowired
	OfferDetailsService offerDetailsService;
	
	//localhost:9090/hoteloffer
	
	@GetMapping
	public ResponseEntity<List<OfferDetails>> getAllOffers() {
		 
		 return new ResponseEntity<>(offerDetailsService.getAllOffers(), HttpStatus.OK); 
	}
	
	//localhost:9090/hoteloffer/100
	
	@GetMapping("{offerId}")
	public ResponseEntity<OfferDetails> getofferById(@PathVariable("offerId") int offerId) {
	
		OfferDetails details=new OfferDetails();
		if (offerDetailsService.isOfferExits(offerId)) {
	
			details=offerDetailsService.getofferById(offerId);
			return new ResponseEntity<OfferDetails>(details,HttpStatus.OK);
		
		} 
		else 
			return new ResponseEntity<OfferDetails>(details,HttpStatus.NO_CONTENT);
		
			
	}
	
	@GetMapping("getOfferByRoomType/{roomType}")
	public ResponseEntity<List<OfferDetails>>  getOfferByRoomType(@PathVariable("roomType") String roomType) {
	
		List<OfferDetails> details;	
		details=offerDetailsService.getOfferByRoomType(roomType);
		return new ResponseEntity<List<OfferDetails>> (details,HttpStatus.OK);
			
	}
	
	@GetMapping("getOfferByRoomTypeAndRoomSize/{roomType}/{roomSize}")
	public ResponseEntity<List<OfferDetails>>  getOfferByRoomTypeAndRoomSize(@PathVariable("roomType") String roomType,@PathVariable("roomSize") String roomSize) {
	
		List<OfferDetails> details;	
		details=offerDetailsService.findByRoomTypeAndRoomSize(roomType,roomSize);
		return new ResponseEntity<List<OfferDetails>> (details,HttpStatus.OK);
			
	}
	
	
	//localhost:9090/hoteloffer
	
	@PostMapping
	public ResponseEntity<String> addOffer(@RequestBody OfferDetails offerDetails) {

		ResponseEntity<String> responseEntity = null;
		
		offerDetailsService.addOffer(offerDetails);
		
		int offerId = offerDetails.getOfferId();
		responseEntity = new ResponseEntity<String>("Offer Added Successfully with Offer Id: "+offerId, HttpStatus.OK);
		
		return responseEntity;
	}
	
	//localhost:9090/hoteloffer/6
	
	@DeleteMapping("{offerId}")
	public ResponseEntity<String> deleteOffer(@PathVariable("offerId") int offerId) {
	
		ResponseEntity<String> responseEntity = null;
		String message = null;
		if (offerDetailsService.isOfferExits(offerId)) {
		
			offerDetailsService.deleteOfferById(offerId);
			message = "Offer with offer id : " + offerId + " deleted successfully ";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		
		} 
		else {
			message = "Offer id : " + offerId + " doesn't exists ";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
			
	}
	
	//localhost:9090/hoteloffer
	
	@PutMapping
	public ResponseEntity<String>  updateOffer(@RequestBody OfferDetails offerDetails) {

		ResponseEntity<String> responseEntity = null;
		int offerId = offerDetails.getOfferId();
		String message = null;
		if (offerDetailsService.isOfferExits(offerId)) {
		
			offerDetailsService.updateOffer(offerDetails);
			message = "Offer with Offer id : " + offerId + " updated successfully";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		
		} else {
			message = "Offer with Offer id : " + offerId + " doesn't exists";
			responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return responseEntity;
	}
	
	
}
