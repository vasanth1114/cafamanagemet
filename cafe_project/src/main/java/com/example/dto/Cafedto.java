package com.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Table(name="menu_items")
public class Cafedto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private int dish_id;
	@Column
	private String dish_name;
	@Column
	private int price;
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
	public Cafedto(int dish_id, String dish_name, int price) {
		super();
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.price = price;
	}
	public Cafedto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Cafe_entity [dish_id=" + dish_id + ", dish_name=" + dish_name + ", price=" + price + "]";
	}

}
