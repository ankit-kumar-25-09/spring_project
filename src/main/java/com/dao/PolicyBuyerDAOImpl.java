package com.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.PolicyBuyer;
import com.repo.PolicyBuyerRepo;

@Service
public class PolicyBuyerDAOImpl implements PolicyBuyerDAO {
	
	@Autowired
	PolicyBuyerRepo policyBuyerRepo;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;

	@Override
	public PolicyBuyer addPolicyBuyer(PolicyBuyer policyBuyer) {
		// TODO Auto-generated method stub
		policyBuyer.setPassword(passEncoder.encode(policyBuyer.getPassword()));
		return policyBuyerRepo.save(policyBuyer);
	}

	@Override
	public PolicyBuyer findPolicyBuyerByEmail(String email) {
		// TODO Auto-generated method stub
		return policyBuyerRepo.findByemail(email);
		
	}

	@Override
	public PolicyBuyer findPolicyBuyerById(Long userId) {
		// TODO Auto-generated method stub
		Optional<PolicyBuyer> optObject= policyBuyerRepo.findById(userId);
		if(optObject.isEmpty()) {
			return null;
		}
		return optObject.get();
		
	}
	
	

}
