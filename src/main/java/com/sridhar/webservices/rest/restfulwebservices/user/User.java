package com.sridhar.webservices.rest.restfulwebservices.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Integer id;
	
	@Size(min=5, message="Name should be between 5 and 30 characters")
	private String name;
	
	@Email(message="Invalid Email Format")
	private String email;
	
	// This field will not be included in JSON
	@JsonIgnore
	private String password;
}
