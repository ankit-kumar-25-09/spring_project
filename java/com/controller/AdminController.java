package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.AdminDAO;
import com.entity.Admin;
import com.models.AuthenticationRequest;
import com.models.AuthenticationResponse;
import com.service.JwtUtil;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping("/register")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminDAO.addAdmin(admin);
	}
	
	@PostMapping("/getAdminDetails/{adminId}")
	public Admin getAdminById(@PathVariable Long adminId) {
		return adminDAO.findAdminById(adminId);
	}
	
	@GetMapping("/getAdminDetailsByCompanyName/{companyName}")
	public Admin getAdminDetailsByCompanyName(@PathVariable String companyName) {
		Admin admin = adminDAO.findAdminByCompanyName(companyName);
		return Admin.builder()
				.adminId(admin.getAdminId()).companyName(companyName).build();
	}
	
	@GetMapping("/login")
	public ResponseEntity loginPolicyBuyer(@RequestBody AuthenticationRequest authReq) {
		Admin admin = adminDAO.findAdminByEmail(authReq.getEmail());
		
		if(admin == null) {
			return new ResponseEntity("Invalid login credentials",HttpStatus.UNAUTHORIZED);
		}
		
	
		if(encoder.matches(authReq.getPassword(), admin.getPassword())) {
			String jwt = jwtUtil.generateToken(String.valueOf(admin.getAdminId()));
			return new ResponseEntity(new AuthenticationResponse(admin.getAdminId(),jwt),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity("Invalid login credentials",HttpStatus.UNAUTHORIZED);	
	}
}
