package com.revature.hms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "customerdetails")
public class Customer {
	private String customerName;
	@Id
	private String customerUserName;
	private String password;
	private String mobileNumber;
	private String email;
	private String gender;
	private int age;
	private String city;
	private String state;
	private String country;
}
