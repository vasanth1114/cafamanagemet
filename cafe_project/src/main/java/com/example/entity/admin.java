package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admin")
public class admin {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int admin_id;
	@Column
	private String admin_name;
	@Column
	private String password;
	
	
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	@Override
	public String toString() {
		return "admin [admin_name=" + admin_name + ", password=" + password + ", admin_id=" + admin_id + "]";
	}
	public admin(String admin_name,String password, int admin_id) {
		super();
		this.admin_name = admin_name;
		this.password = password;
		this.admin_id = admin_id;
	}
	public admin() {
		super();
		
	}
	

}

