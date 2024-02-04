package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.admin;

@Repository
public interface admin_repos extends JpaRepository<admin,Integer>{

}

