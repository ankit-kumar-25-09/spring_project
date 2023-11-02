package com.dao;

import com.entity.PolicyBuyer;

public interface PolicyBuyerDAO {
	
	public PolicyBuyer addPolicyBuyer(PolicyBuyer policyBuyer);
	public PolicyBuyer findPolicyBuyerByEmail(String email);
	public PolicyBuyer findPolicyBuyerById(Long userId);

}
