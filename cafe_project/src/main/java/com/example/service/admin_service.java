package com.example.service;

import java.util.List;

import com.example.entity.admin;

public interface admin_service {
	public admin saveadmin(admin obj);
	public List<admin>getAlladmin();
	public admin getadminById(int admin_id);
	public admin updateadmin(admin obj,int admin_id);
	public void deleteadmin(int admin_id);
	//-------------validation------------
	public int loginvalidation(String username, String Password);
}

