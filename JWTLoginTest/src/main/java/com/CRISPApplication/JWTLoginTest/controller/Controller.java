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
@CrossOrigin("*")//enables front applications from different ports to access this backend
@RequestMapping("/api/v1")//url of this application
public class Controller {
	@Autowired
	private PersonService pServ;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager aMan;
	
	@Autowired
	private StoreRecordService strRecServ;
	
	@GetMapping("/")//the "/" request mapping will return a string
	public String welcome() {
		return "Hi you logged in!";
			
	}
	
	@GetMapping("/records")//the "/records" request mapping will return a list of all the StoreRecords in the DB
	public ResponseEntity<List<StoreRecord>> getPersons(){
		List<StoreRecord> records = strRecServ.findAll();//call the findAll() command
		if(records==null) {//check if any were returned
			System.out.println("records is null");
		}
		return new ResponseEntity<List<StoreRecord>>(records, HttpStatus.OK);//return a new entity and set HttpStatus to ok.
	}
	@PostMapping("/recordRequest")//The "/recordRequest" post mapping will return a list of of StoreRecords that match the request
	public ResponseEntity<List<StoreRecord>> get(@RequestBody RecordRequest recRequest){
		System.out.println("hey /records/{id} was touched by get");
		List<StoreRecord> record = strRecServ.findByZipItem(recRequest.getZipcode(), recRequest.getItem());//call the findById() command
		return new ResponseEntity<List<StoreRecord>>(record, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
	}
	
	@PostMapping("/authenticate")//The "/authenticate" post mapping will return a token if the username and password can be authenticated
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		
		try {//try to authenticate a request with the given username and pasword
			aMan.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		}
		catch(Exception e) {//if it fails give error message
			throw new Exception("rip invalid credentials");
		}
		return jwtUtils.generateToken(authRequest.getUsername());//this generates the token based on username and sends it back
	}
}
