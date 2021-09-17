package com.revature.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.hms.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Optional<Admin> findByAdminIdAndAdminPassword(int adminId, String adminPassword);
}
