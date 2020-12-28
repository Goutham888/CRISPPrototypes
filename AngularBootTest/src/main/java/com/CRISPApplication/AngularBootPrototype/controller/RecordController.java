package com.CRISPApplication.AngularBootPrototype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRISPApplication.AngularBootPrototype.model.StoreRecord;
import com.CRISPApplication.AngularBootPrototype.service.RecordService;

@CrossOrigin("*")
@RestController//marking it as a rest controller
@RequestMapping("/api/v1")//not quite sure what this is
public class RecordController {
	
	@Autowired
	RecordService recordService;//bringing in the recordService interface
	
	@GetMapping("/records")//when records is called
	public ResponseEntity<List<StoreRecord>> get(){
		System.out.println("hey /records was touched by get");
		List<StoreRecord> records = recordService.findAll();//call the findAll() command
		if(records==null) {
			System.out.println("records is null");
		}
		return new ResponseEntity<List<StoreRecord>>(records, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
		//I still don't understand how findAll will get called when you use the interface
	}
	
	
	@PostMapping("/records")//when records is called
	public ResponseEntity<StoreRecord> save(@RequestBody StoreRecord record){
		System.out.println("hey /records was touched by post");
		StoreRecord savedRecord = recordService.save(record);//call the save() command
		//I still don't understand how the method gets called when we use the interface instead of the class that extends it
		
		return new ResponseEntity<StoreRecord>(savedRecord, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
		//I still don't understand how findAll will get called when you use the interface
		//kinda an issue with this is that it doesn't automatically update the record_id field
		//every time you run it it updates the value
		//if the value you want it 5, it the method needs to be called 5 times for it to work
	}
	
	@GetMapping("/records/{id}")//when records is called
	public ResponseEntity<StoreRecord> get(@PathVariable("id") Long id){
		System.out.println("hey /records/{id} was touched by get");
		StoreRecord record = recordService.findById(id);//call the findById() command
		return new ResponseEntity<StoreRecord>(record, HttpStatus.OK);//return a new entity and set HttpStatus to ok. 
		//I still don't understand how findById() will get called when you use the interface
	}
	
}
