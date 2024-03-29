package com.spring.mvc.security.dao;


import java.util.ArrayList;

import org.bson.Document;
import org.springframework.stereotype.Component;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.spring.mvc.security.pojo.StoreRecord;

@Component
public class RecordsDao {
	
	//This is the login information for the current DB don't steal it
		/*String userName = "postgres";
		String password = "PostgreSQL_2230?";
		String hostname = "localhost";
		String port = "5433";
		String dbName="CRISPSpringMVCPrototype";
		String jdbcUrl = "jdbc:postgresql://"+hostname+":"+port+"/"+dbName;*/
		
	public ArrayList<StoreRecord> retrieveRecords(String store, String item) {
		String quantity="error";
		String address="error";
		ArrayList<StoreRecord> results = new ArrayList<StoreRecord>();
		
				try {//Java forced me to do this to avoid Exceptions from the DB
					MongoClient mongo = new MongoClient( "localhost" , 27017 );
					MongoDatabase CRISPDB = mongo.getDatabase("CRISPMongoDBPrototype");
					MongoCollection<Document> productColl = CRISPDB.getCollection("products");
					
					FindIterable<Document> prodResults = productColl.find(new Document("store_name", store).append("item", item));
		            MongoCursor<Document> cursor = prodResults.iterator();
					while(cursor.hasNext()) {
						Document doc = cursor.next();
						 switch(doc.getInteger("quantity")) {
				         case 1 :
				            quantity="Low";
				            break;
				         case 2 :
				        	 quantity="Medium";
					         break;
				         case 3 :
				        	 quantity="High";
				            break;
				      }//end of switch statement
						 
						 address=doc.getInteger("store_street_pos")+" "+doc.getString("store_street")+" "+
						doc.getString("city")+" "+doc.getString("state")+" "+doc.getInteger("zipcode");
						 
						 results.add(new StoreRecord(store, address, quantity, item));
								 
						 System.out.println("There is "+quantity+" "+item+" at "+ store +" at "+address);
					 }
					 return results;
					 
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return results;
				} 
	}
}
