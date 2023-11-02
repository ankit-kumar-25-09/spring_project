package com.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Long>{
	Admin findByemail(String email);
	Admin findBycompanyName(String companyName);
}
