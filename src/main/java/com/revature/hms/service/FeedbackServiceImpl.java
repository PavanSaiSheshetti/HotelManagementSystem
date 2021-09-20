package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Feedback;
import com.revature.hms.repository.FeedbackRepository;


@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public boolean addFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
		return true;
	}

	@Override
	public boolean deleteFeedback(int feedbackId) {
		feedbackRepository.deleteById(feedbackId);
		return true;
	}

	@Override
	public Feedback getFeedbackById(int feedbackId) {
		Optional<Feedback> feedbackData=feedbackRepository.findById(feedbackId);
		Feedback feedback=feedbackData.get();
		return feedback;
	}

	@Override
	public List<Feedback> getAllFeedbacks() {
		List<Feedback> feedback=(List<Feedback>) feedbackRepository.findAll();
		return feedback;
	}
	
	

}
