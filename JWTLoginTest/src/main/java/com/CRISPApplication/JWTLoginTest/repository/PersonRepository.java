package com.CRISPApplication.JWTLoginTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRISPApplication.JWTLoginTest.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	//important to remember
		//--The second variable in the Jpa declaration is the wrapper class for the primary key in your entity
		//--The repository object is always an interface
	
	Person findByUsername(String username);//how does this method work at all?
	//there is no definition for it to do anything with
	//howw????
}
