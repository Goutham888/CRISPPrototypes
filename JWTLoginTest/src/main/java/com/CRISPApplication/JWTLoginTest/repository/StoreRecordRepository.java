package com.CRISPApplication.JWTLoginTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.CRISPApplication.JWTLoginTest.entity.StoreRecord;


public interface StoreRecordRepository extends JpaRepository<StoreRecord, Long>{
	
	//The @Query passes a custom query to the Repository
	@Query(value="select * from angular_record_test where zipcode=?1 and item=?2", nativeQuery=true)
	List<StoreRecord> findByZipItem(Integer zipcode, String item);
}
