package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PolicyBuyer {
	@Id
	@GeneratedValue
	private Long userId;
	private String name;
	private String email;
	private String password;
	private String securityQuestion;
	private String securityAnswer;

}
