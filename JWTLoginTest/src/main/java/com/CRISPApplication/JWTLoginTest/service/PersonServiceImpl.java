package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRISPApplication.JWTLoginTest.entity.Person;
import com.CRISPApplication.JWTLoginTest.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository pRep;
	
	@Override
	public List<Person> findAll() {
			//using the Record repository findAll() method in the override for findAll in RecordService
			return pRep.findAll();
		
	}

}
