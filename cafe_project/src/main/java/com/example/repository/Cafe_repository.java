package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Cafe;

@Repository
public interface Cafe_repository extends JpaRepository<Cafe, Integer> 
{

}
