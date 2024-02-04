package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="menu_items")
public class Cafe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="dish_id")
	private int dish_id;//
	@Column
	private String dish_name;
	@Column
	private int price;
	@Column(name = "type")
	private String type;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
	@Lob
	@Column(name = "picByte", length = Integer.MAX_VALUE)
	private byte[] picByte;
	
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Cafe() {
		super();
	}

	public Cafe(int dish_id, String dish_name, int price, String type, byte[] picByte) {
		super();
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.price = price;
		this.type = type;
		this.picByte = picByte;
	}
	@Override
	public String toString() {
		return "Cafe_entity [dish_id=" + dish_id + ", dish_name=" + dish_name + ", price=" + price + "]";
	}
	

}
