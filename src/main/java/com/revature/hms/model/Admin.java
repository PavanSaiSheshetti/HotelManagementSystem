package com.revature.hms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class Admin {

	
	@Id
	private int adminId;
	@Column
	private String adminPassword;
}
