package com.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.PolicyBuyer;

@Repository
public interface PolicyBuyerRepo extends CrudRepository<PolicyBuyer, Long>{
	PolicyBuyer findByemail(String email);
}
