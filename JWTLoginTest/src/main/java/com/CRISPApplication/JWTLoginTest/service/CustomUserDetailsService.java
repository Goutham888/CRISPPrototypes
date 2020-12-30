package com.CRISPApplication.JWTLoginTest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.CRISPApplication.JWTLoginTest.entity.Person;
import com.CRISPApplication.JWTLoginTest.repository.PersonRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	PersonRepository pRep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person p = pRep.findByUsername(username);
		return new User(p.getUsername(), p.getPassword(), new ArrayList<>());//not doing roles so that Array list is empty
		
	}

}
