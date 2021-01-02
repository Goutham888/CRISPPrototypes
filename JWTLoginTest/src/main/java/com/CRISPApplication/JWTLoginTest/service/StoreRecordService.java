package com.CRISPApplication.JWTLoginTest.service;

import java.util.List;

import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;


public interface StoreRecordService {//the interface that determines methods used by the controller to get data
	List<StoreRecord> findAll();
	
	List<StoreRecord> findByZipItem(Integer zipcode, String item);
}
