package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;
import com.CRISPApplication.JWTLoginTest.repository.StoreRecordRepository;

@Service
public class StoreRecordServiceImpl implements StoreRecordService{
	
	@Autowired
	StoreRecordRepository strRecRep;
	
	@Override
	public List<StoreRecord> findAll() {
		return strRecRep.findAll();
	}

	@Override
	public StoreRecord findByZipItem(Integer zipcode, String item) {
		return strRecRep.findByZipItem(zipcode, item);
	}
	
	
	
}
