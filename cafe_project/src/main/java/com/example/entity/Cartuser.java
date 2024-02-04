package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_table")
public class Cartuser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;
	
	@Column(name = "dish_id")
	private int dishid;
	
	@Column(name = "quantity")
	private long quantity;

	
	@Column(name="userid")
    private int userid;
	
	 @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'unordered'")
	    private String isordered;
	 
	 
	public String getIsordered() {
		return isordered;
	}

	public void setIsordered(String isordered) {
		this.isordered = isordered;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getDishid() {
		return dishid;
	}

	public void setDishid(int dishid) {
		this.dishid = dishid;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Cartuser(int cartId, int dishid, long quantity, int userid , String isordered) {
		super();
		this.cartId = cartId;
		this.dishid = dishid;
		this.quantity = quantity;
		this.userid = userid;
		this.isordered = isordered;
	}

	public Cartuser() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cartuser [cartId=" + cartId + ", dishid=" + dishid + ", quantity=" + quantity + ", userid=" + userid
				+ ", isordered=" + isordered + "]";
	}


	

}

