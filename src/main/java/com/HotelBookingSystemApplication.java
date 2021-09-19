package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class HotelBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBookingSystemApplication.class, args);
	}

	@Autowired
	private static JavaMailSender mailSender;
	 
	@Autowired
	public HotelBookingSystemApplication(JavaMailSender s) {
		this.mailSender = s;
	}

	public void sendMail(String from, String toReceiver, String subject, String message) {
	
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(toReceiver);
		msg.setSubject(subject);
		msg.setText(message);
		mailSender.send(msg);
	}

}
