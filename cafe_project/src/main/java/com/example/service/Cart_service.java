package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Cafe;
import com.example.entity.Cartuser;

public interface Cart_service {
		public Cartuser savecart(Cartuser values);
		public List<Cartuser> getAllcart();// view
		public List<Cartuser> getCartById(int userId);//particular view
		public Cartuser updatemenu(Cartuser values, int cart_id);
		public void deletecart(int cart_id);
		public int ordercart(int userId);
}
