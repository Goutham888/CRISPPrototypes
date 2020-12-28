package com.CRISPApplication.AngularBootPrototype.service;

import java.util.List;
import com.CRISPApplication.AngularBootPrototype.model.StoreRecord;

//This is a service interface implemented by RecordServiceImpl.java
public interface RecordService {
	//this method is overriden in that file
	List<StoreRecord> findAll();
	
	StoreRecord save(StoreRecord record);
}
