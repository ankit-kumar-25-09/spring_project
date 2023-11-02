package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Admin {
	@Id
	@GeneratedValue
	private Long adminId;
	private String password;
	private String name;
	private String companyName;
	private String email;
	private Long phone;
	private String securityQuestion;
	private String securityAnswer;

}
