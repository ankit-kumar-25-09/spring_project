package com.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.Admin;
import com.repo.AdminRepo;

@Service
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;

	@Override
	public Admin addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepo.save(admin);
	}

	@Override
	public Admin findAdminByEmail(String email) {
		// TODO Auto-generated method stub
		return adminRepo.findByemail(email);
	}

	@Override
	public Admin findAdminById(Long adminId) {
		// TODO Auto-generated method stub
		Optional<Admin> op = adminRepo.findById(adminId);
		if(op.isPresent())return op.get();
		return null;
	}

	@Override
	public Admin findAdminByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		return adminRepo.findBycompanyName(companyName);
	}

}
