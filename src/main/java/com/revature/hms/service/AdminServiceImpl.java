package com.revature.hms.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.revature.hms.model.Admin;
import com.revature.hms.repository.AdminRepository;



@Service
public class AdminServiceImpl implements AdminService 
{
	
	/*
	 * @Autowired ReceptionistRepository receptionistRepository;
	 * 
	 * @Autowired
	 * 
	 * @PersistenceContext private EntityManager em;
	 */

	@Autowired
	AdminRepository adminRepository; 
	

	@Override
	public boolean adminLogin(int adminId, String adminPassword) {
		Optional<Admin> adminData= adminRepository.findByAdminIdAndAdminPassword(adminId, adminPassword);
		return adminData.isPresent();
	}


	@Override
	public Admin getAdminById(int adminId) {
		Optional<Admin> adminData=adminRepository.findById(adminId);
		Admin admin= adminData.get();
		return admin;
	}

	
	/*
	 * @Autowired DataSource dataSource; private JdbcTemplate jdbcTemplate; private
	 * SimpleJdbcCall password;
	 */
    

}
