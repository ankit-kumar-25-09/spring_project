package com.dao;

import com.entity.Admin;
import com.entity.Admin;

public interface AdminDAO {

	public Admin addAdmin(Admin admin);
	public Admin findAdminByEmail(String email);
	public Admin findAdminById(Long adminId);
	public Admin findAdminByCompanyName(String companyName);
}
