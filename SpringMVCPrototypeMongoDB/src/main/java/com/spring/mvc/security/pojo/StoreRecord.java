package com.spring.mvc.security.pojo;

public class StoreRecord {
	private String store;
	private String address;
	private String quantity;
	private String item;
	
	public StoreRecord(String sto, String add, String quant, String it) {
		store=sto;
		address=add;
		quantity=quant;
		item=it;
	}
	
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	
	
}
