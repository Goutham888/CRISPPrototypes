package com.CRISPApplication.JWTLoginTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRISPApplication.JWTLoginTest.entity.AuthRequest;
import com.CRISPApplication.JWTLoginTest.entity.Person;
import com.CRISPApplication.JWTLoginTest.entity.RecordRequest;
import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;
import com.CRISPApplication.JWTLoginTest.service.PersonService;
import com.CRISPApplication.JWTLoginTest.service.StoreRecordService;
import com.CRISPApplication.JWTLoginTest.util.JwtUtils;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")//not quite sure what this is
public class Controller {
	@Autowired
	private PersonService pServ;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager aMan;
	
	@Autowired
	private StoreRecordService strRecServ;
	
	@GetMapping("/")
	public String welcome() {
		return "Hi you logged in!";
			
	}
	
	@GetMapping("/records")
	public ResponseEntity<List<StoreRecord>> getPersons(){
		List<StoreRecord> records = strRecServ.findAll();//call the findAll() command
		if(records==null) {
			System.out.println("records is null");
		}
		return new ResponseEntity<List<StoreRecord>>(records, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
		//I still don't understand how findAll will get called when you use the interface
	}
	@PostMapping("/recordRequest")//when records is called
	public ResponseEntity<List<StoreRecord>> get(@RequestBody RecordRequest recRequest){
		System.out.println("hey /records/{id} was touched by get");
		List<StoreRecord> record = strRecServ.findByZipItem(recRequest.getZipcode(), recRequest.getItem());//call the findById() command
		return new ResponseEntity<List<StoreRecord>>(record, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
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
