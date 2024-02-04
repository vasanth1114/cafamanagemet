package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="User_Details")
public class EntityEx {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userid;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private int phoneno;
	@Column
	private String email;
	@Column
	private String city;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public int getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "EntityEx [userid=" + userid + ", username=" + username + ", password=" + password + ", phoneno="
				+ phoneno + ", email=" + email + ", city=" + city + "]";
	}
	public EntityEx(int userid, String username, String password, int phoneno, String email, String city) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.phoneno = phoneno;
		this.email = email;
		this.city = city;
	}
	public EntityEx() {
		super();
		// TODO Auto-generated constructor stub
	}
		

}

