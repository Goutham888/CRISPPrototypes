package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import com.CRISPApplication.JWTLoginTest.entity.Person;

public interface PersonService {//the interface that determines methods used by the controller to get data
	
	List<Person> findAll();
}
