package com.CRISPApplication.JWTLoginTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRISPApplication.JWTLoginTest.entity.AuthRequest;
import com.CRISPApplication.JWTLoginTest.entity.Person;
import com.CRISPApplication.JWTLoginTest.service.PersonService;
import com.CRISPApplication.JWTLoginTest.util.JwtUtils;

@RestController
@CrossOrigin("*")
public class LoginController {
	@Autowired
	private PersonService pServ;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager aMan;
	
	@GetMapping("/")
	public String welcome() {
		return "Hi you logged in!";
			
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Person>> getPersons(){
		List<Person> people = pServ.findAll();//call the findAll() command
		if(people==null) {
			System.out.println("records is null");
		}
		return new ResponseEntity<List<Person>>(people, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
		//I still don't understand how findAll will get called when you use the interface
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		
		try {//try to authenticate a request with the given username and pasword
			aMan.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		}
		catch(Exception e) {//if it fails give error message
			throw new Exception("rip invalid credentials");
		}
		return jwtUtils.generateToken(authRequest.getUsername());
	}
}
