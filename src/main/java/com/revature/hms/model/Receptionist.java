package com.revature.hms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "receptionists")
@NoArgsConstructor
@AllArgsConstructor

/*
 * @NamedStoredProcedureQueries({
 * 
 * @NamedStoredProcedureQuery(name = "getUniquePassword",procedureName
 * ="generate_password") })
 */
public class Receptionist 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int receptionistId;
	
	@Column(nullable=false)
	private String receptionistName;
	
	@Column(nullable=false)
	private String receptionistPassword;
	
	@Column(nullable=false)
	private int receptionistAge;
	
	@Column(nullable=false, unique=true)
	private String receptionistPhoneNumber;
	
	@Column(nullable=false, unique=true)
	private String receptionistEmail;
	
	@Column(nullable=false)
	private String address;
	
	
	private String experience;
	
	@Column(nullable=false)
	private int salary;
	

}
