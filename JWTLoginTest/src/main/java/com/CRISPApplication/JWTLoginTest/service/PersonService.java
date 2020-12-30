package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import com.CRISPApplication.JWTLoginTest.entity.Person;

public interface PersonService {
	
	List<Person> findAll();
}
