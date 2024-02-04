package com.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dish_id")
    private int id;
   @ManyToOne(targetEntity=Cafe.class,cascade= {CascadeType.MERGE},fetch=FetchType.EAGER)
  // @OneToOne(cascade= CascadeType.ALL)
   @JoinColumn(name="fk_dish_id",referencedColumnName="dish_id")
    private Cafe menu_items;
    @Column
    private String dishName;
    @Column
    private Long dishPrice;
    @Column
    private Long totalBill;
	public Order_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order_entity(int id, Cafe menu_items, String dishName, Long dishPrice, Long totalBill) {
		super();
		this.id = id;
		this.menu_items = menu_items;
		this.dishName = dishName;
		this.dishPrice = dishPrice;
		this.totalBill = totalBill;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Cafe getMenu_items() {
		return menu_items;
	}
	public void setMenu_items(Cafe menu_items) {
		this.menu_items = menu_items;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public Long getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(Long dishPrice) {
		this.dishPrice = dishPrice;
	}
	public Long getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(Long totalBill) {
		this.totalBill = totalBill;
	}
	@Override
	public String toString() {
		return "Order_entity [id=" + id + ", menu_items=" + menu_items + ", dishName=" + dishName + ", dishPrice="
				+ dishPrice + ", totalBill=" + totalBill + "]";
	}
	
   
}

