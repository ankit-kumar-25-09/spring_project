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

import com.dao.PolicyBuyerDAO;
import com.entity.PolicyBuyer;
import com.models.AuthenticationRequest;
import com.models.AuthenticationResponse;
import com.models.PolicyBuyerDetailsResponse;
import com.models.TokenValidationResponse;
import com.service.JwtUtil;

@RestController
@RequestMapping("/policyBuyer")
public class PolicyBuyerController {
	
	@Autowired
	PolicyBuyerDAO policyBuyerDAO;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@PostMapping("/register")
	public PolicyBuyer addPolicyBuyer(@RequestBody PolicyBuyer policyBuyer) {
		return policyBuyerDAO.addPolicyBuyer(policyBuyer);
	}
	
	@GetMapping("/login")
	public ResponseEntity loginPolicyBuyer(@RequestBody AuthenticationRequest authReq) {
		PolicyBuyer policyBuyer = policyBuyerDAO.findPolicyBuyerByEmail(authReq.getEmail());
		
		if(policyBuyer == null) {
			return new ResponseEntity("Invalid login credentials",HttpStatus.UNAUTHORIZED);
		}
		
	
		if(encoder.matches(authReq.getPassword(), policyBuyer.getPassword())) {
			String jwt = jwtUtil.generateToken(String.valueOf(policyBuyer.getUserId()));
			return new ResponseEntity(new AuthenticationResponse(policyBuyer.getUserId(),jwt),HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity("Invalid login credentials",HttpStatus.UNAUTHORIZED);	
	}
	
	@GetMapping("/validateToken/{userId}/{token}")
	public ResponseEntity validateToken(@PathVariable String token,@PathVariable String userId) {
		boolean validated = jwtUtil.validateToken(token, userId);
		if(validated) {
			return new ResponseEntity(new TokenValidationResponse(true),HttpStatus.OK);
		}
		return new ResponseEntity(new TokenValidationResponse(false),HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/getPolicyBuyerDetails/{userId}")
	public PolicyBuyerDetailsResponse getUserDetails(@PathVariable Long userId) {
		PolicyBuyer policyBuyer = policyBuyerDAO.findPolicyBuyerById(userId);
		if(policyBuyer == null)return null;
		return new PolicyBuyerDetailsResponse(policyBuyer.getName(),policyBuyer.getEmail());
	}
	
	

}
