package com.revature.hms.service;

import com.revature.hms.model.Admin;

public interface AdminService 
{
	public boolean adminLogin(int adminId, String adminPassword);
	public Admin getAdminById(int adminId);
}
