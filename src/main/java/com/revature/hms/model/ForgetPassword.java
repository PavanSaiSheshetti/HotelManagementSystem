package com.revature.hms.model;



import lombok.Data;

@Data
public class ForgetPassword {
	private String customerUserName;
	private String mobileNumber; 
	private String password;
}
