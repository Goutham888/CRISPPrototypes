package com.CRISPApplication.JWTLoginTest.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//this defines storerecord as a table in Postgresql (see application.properties to find where we defined the DB)
@Table(name = "AngularRecordTest")
public class StoreRecord {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//this makes store_id a unique primary keu
	private long record_id;//all of these variables are columns in the table
	private String store_name;
	private int store_street_pos;
	private String store_street;
	private String city;
	private String state;
	private int zipcode;
	private String item;
	private int quantity;
	
	//just getters and setters
	public long getRecord_id() {
		return record_id;
	}
	public void setRecord_id(long record_id) {
		this.record_id = record_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public int getStore_street_pos() {
		return store_street_pos;
	}
	public void setStore_street_pos(int store_street_pos) {
		this.store_street_pos = store_street_pos;
	}
	public String getStore_street() {
		return store_street;
	}
	public void setStore_street(String store_street) {
		this.store_street = store_street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
	
}
