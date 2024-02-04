package com.example.service_implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Cafe;
import com.example.entity.Order_entity;
import com.example.exception.IdNotValidException;
import com.example.repository.Order_repository;
import com.example.service.Order_service;

import jakarta.persistence.criteria.Order;

@Service
public class orderserviceLmp implements Order_service{
	
	@Autowired
	private Order_repository orderRepository;
	
	@Override
	public Order_entity saveorders(Order_entity order) {
		return orderRepository.save(order);
	}
	//--------------------------------view All---------------------------
	@Override
	public List<Order_entity> getAllorders() {

		return orderRepository.findAll();
	}
	//-------------------------------View By Id---------------------------
	@Override
	public Order_entity getorderById(int id) {

		return orderRepository.findById(id).orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", id));
	}
	//--------------------------------update------------------------

	@Override
	public Order_entity updateorder(Order_entity order, int id) {

		Order_entity obj = orderRepository.findById(id)
				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", id));
		obj.setId(order.getId());
		obj.setDishName(order.getDishName());
		obj.setDishPrice(order.getDishPrice());
		obj.setTotalBill(order.getTotalBill());
		 Order_entity save = orderRepository.save(obj);
		return save;
	}
	//-------------------------------Delete-----------------------------------------------
	@Override
	public void deleteorder(int id) {
		Order_entity obj1 = orderRepository.findById(id)
				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", id));
		orderRepository.delete(obj1);
	}
	
}

	
	
	



