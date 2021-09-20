package com.revature.hms.service;

import java.util.List;

import com.revature.hms.model.Feedback;



public interface FeedbackService {
	public boolean addFeedback(Feedback feedback);
	public boolean deleteFeedback(int feedbackId);
	public Feedback getFeedbackById(int feedbackId);
	public List<Feedback> getAllFeedbacks();
}
