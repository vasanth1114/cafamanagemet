package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Order_entity;

public interface Order_repository extends JpaRepository <Order_entity,Integer> {

}

