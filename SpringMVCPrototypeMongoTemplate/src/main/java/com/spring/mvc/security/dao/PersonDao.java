package com.spring.mvc.security.dao;


import java.util.ArrayList;

import org.bson.Document;
import org.springframework.stereotype.Component;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.spring.mvc.security.pojo.Person;
import com.spring.mvc.security.pojo.StoreRecord;

@Component
public class PersonDao {
	
	//This is the login information for the current DB don't steal it
		/*String userName = "postgres";
		String password = "PostgreSQL_2230?";
		String hostname = "localhost";
		String port = "5433";
		String dbName="CRISPSpringMVCPrototype";
		String jdbcUrl = "jdbc:postgresql://"+hostname+":"+port+"/"+dbName;*/
		
	public Person getPerson(String username) {
		Person user = new Person();
		
				try {//Java forced me to do this to avoid Exceptions from the DB
					MongoClient mongo = new MongoClient( "localhost" , 27017 );
					MongoDatabase CRISPDB = mongo.getDatabase("CRISPMongoDBPrototype");
					MongoCollection<Document> userColl = CRISPDB.getCollection("users");
					
					FindIterable<Document> userResults = userColl.find(new Document("username", username));
		            MongoCursor<Document> cursor = userResults.iterator();
					while(cursor.hasNext()) {
						Document doc = cursor.next();
						//user.setId(doc.getInteger("user_id"));
						System.out.println(doc.getDouble("user_id"));
						user.setUsername(doc.getString("username"));
						user.setPassword(doc.getString("password"));
						user.setEnabled(doc.getBoolean("enabled"));

					 }
						
					MongoCollection<Document> roleColl = CRISPDB.getCollection("users");
					
					FindIterable<Document> roleResults = roleColl.find(new Document("user_id", user.getId()));
		            MongoCursor<Document> roleCursor = roleResults.iterator();
					while(roleCursor.hasNext()) {
						Document role_doc = cursor.next();
						user.setRole(role_doc.getString("authority"));

					 }
					
					 return user;
					 
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return user;
				} 
	}
}
