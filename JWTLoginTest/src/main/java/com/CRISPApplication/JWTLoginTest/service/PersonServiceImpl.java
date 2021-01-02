package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRISPApplication.JWTLoginTest.entity.Person;
import com.CRISPApplication.JWTLoginTest.repository.PersonRepository;

@Service//defined as a service, lights up in component scan
public class PersonServiceImpl implements PersonService{//the implementation of Person service that gives a body 
														//to the methods defined in PersonService
	
	@Autowired
	private PersonRepository pRep;
	
	@Override
	public List<Person> findAll() {
			//using the Record repository findAll() method in the override for findAll in RecordService
			return pRep.findAll();
		
	}

}
