package com.CRISPApplication.JWTLoginTest.entity;


public class RecordRequest {
	
	
	
	private Integer zipcode;
	private String item;
	
	public RecordRequest() {
	}
	
	public RecordRequest(Integer zipcode, String item) {
		this.zipcode = zipcode;
		this.item = item;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	
}
