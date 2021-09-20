package com.revature.hms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.hms.model.Feedback;
import com.revature.hms.service.FeedbackService;



@RestController
@RequestMapping("feedback")
@CrossOrigin(origins="http://localhost:4200")
public class FeedbackController {
	
	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping
	public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback){
		ResponseEntity<String> responseEntity=null;
		String message=null;
		feedbackService.addFeedback(feedback);
			message="feedback is added succesfully";
			responseEntity=new ResponseEntity<String>(message,HttpStatus.OK);


		return responseEntity;
		
	}
	

	
	@DeleteMapping("{feedbackId}")
	public ResponseEntity<String> deleteProducts(@PathVariable("feedbackId")int feedbackId){
		ResponseEntity<String> responseEntity=null;
		String message=null;
	
		feedbackService.deleteFeedback(feedbackId);
			 message = "product with productId :"+feedbackId+" is deleted";

         return responseEntity;
		
	
		
	}
	
	
	
	
	@GetMapping
	public ResponseEntity<List<Feedback>> getProducts(@RequestParam(required = false) String productName){
		ResponseEntity<List<Feedback>> responseEntity=null;
		List<Feedback> feedbacks=new ArrayList<Feedback>();

		if(productName==null) {
			feedbacks= feedbackService.getAllFeedbacks();
			
			
	
			
			
		}
		if(feedbacks.size()==0) {
			 responseEntity=new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.NO_CONTENT);
		}else
		{
			 responseEntity=new ResponseEntity<List<Feedback>>(feedbacks,HttpStatus.OK);
		}
		
		return responseEntity;
		
	}
	

}
