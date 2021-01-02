package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;
import com.CRISPApplication.JWTLoginTest.repository.StoreRecordRepository;

@Service//defined as a service, lights up in component scan
public class StoreRecordServiceImpl implements StoreRecordService{//the implementation of Person service that gives a body 
																//to the methods defined in PersonService
	
	@Autowired
	StoreRecordRepository strRecRep;
	
	@Override
	public List<StoreRecord> findAll() {
		return strRecRep.findAll();
	}

	@Override
	public List<StoreRecord> findByZipItem(Integer zipcode, String item) {
		return strRecRep.findByZipItem(zipcode, item);
	}
	
	
	
}
