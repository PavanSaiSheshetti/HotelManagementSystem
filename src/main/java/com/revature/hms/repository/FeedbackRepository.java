package com.revature.hms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.hms.model.Feedback;


public interface FeedbackRepository extends CrudRepository<Feedback,Integer>{
	public List<Feedback> findByFeedbackId(int feedbackId);
}
