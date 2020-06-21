package com.sridhar.webservices.rest.restfulwebservices.user;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private Integer id;
	private String name;
	private String email;
}
