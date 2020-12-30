package com.CRISPApplication.JWTLoginTest.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity//this defines storerecord as a table in Postgresql (see application.properties to find where we defined the DB)
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//this makes store_id a unique primary keu
	private long id;
	private String username;
	private String password;
	
	
	public Person(){
		//default constructor, without this it throws and error
	}
	public Person(long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
