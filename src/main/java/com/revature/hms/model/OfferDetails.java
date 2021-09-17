package com.revature.hms.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "hoteloffers")
public class OfferDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int offerId;
	
	@Column(nullable=false)
	private String roomType;
	
	@Column(nullable=false)
	private String roomSize;
	
	@Column(nullable=false)
	private String offerName;

	@Temporal(TemporalType.DATE)
	private Date offerFromDate;
	
	@Temporal(TemporalType.DATE)
	private Date offerToDate;
	
	@Column(nullable=false)
	private String offerDetails;
	
	private String termsAndConditions;
	
	@Column(nullable=false)
	private int discountPercentage;
	
}

