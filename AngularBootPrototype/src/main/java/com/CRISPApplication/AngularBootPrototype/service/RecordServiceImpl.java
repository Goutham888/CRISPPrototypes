package com.CRISPApplication.AngularBootPrototype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CRISPApplication.AngularBootPrototype.model.StoreRecord;
import com.CRISPApplication.AngularBootPrototype.repository.RecordRepository;

//labelling it as a service
@Service
public class RecordServiceImpl implements RecordService {
	
	//bringing in record repository
	@Autowired
	RecordRepository rRep;	
	
	@Override
	public List<StoreRecord> findAll() {
		//using the Record repository findAll() method in the override for findAll in RecordService
		return rRep.findAll();
	}

	@Override
	public StoreRecord save(StoreRecord record) {
		StoreRecord savedRecord = rRep.save(record);//uses the RecordRepository's save method 
		//how is this method called when RecordRepository is an interface???
		return savedRecord;
	}

}
