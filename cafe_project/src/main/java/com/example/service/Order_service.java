package com.example.service;

import java.util.List;
import com.example.entity.Order_entity;

public interface Order_service {
	public Order_entity saveorders(Order_entity order);
	public List<Order_entity> getAllorders();// view
	public Order_entity getorderById(int id);//particular view
	public Order_entity updateorder(Order_entity order,int id);
	public void deleteorder(int id);
}

