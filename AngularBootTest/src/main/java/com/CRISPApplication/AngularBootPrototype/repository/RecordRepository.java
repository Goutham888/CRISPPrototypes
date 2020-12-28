package com.CRISPApplication.AngularBootPrototype.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRISPApplication.AngularBootPrototype.model.StoreRecord;

@Repository
public interface RecordRepository extends JpaRepository<StoreRecord, Long> {
//RecordRepository implements the JpaRepository interface which requires an entity object and its primary key datatype between the <>'s
	

}
