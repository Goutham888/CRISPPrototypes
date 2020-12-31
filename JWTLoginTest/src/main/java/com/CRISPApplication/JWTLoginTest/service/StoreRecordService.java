package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;


public interface StoreRecordService {
	List<StoreRecord> findAll();
	
	StoreRecord findByZipItem(Integer zipcode, String item);
}
