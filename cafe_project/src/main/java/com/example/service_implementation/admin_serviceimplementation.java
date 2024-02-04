package com.example.service_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dbutil.Dbutil;
import com.example.entity.admin;
import com.example.exception.IdNotValidException;
import com.example.repository.admin_repos;
import com.example.service.admin_service;
@Service
public  class admin_serviceimplementation implements admin_service {
 @Autowired
 public admin_repos repos;
 Connection connection;
 int flag=0;
 public admin_serviceimplementation() throws SQLException {
	 super();
	 this.repos=repos;
	 connection=Dbutil.getConnection();
 }
	@Override
	public admin saveadmin(admin obj) {
		return repos.save(obj);
	}
	@Override
	public List<admin> getAlladmin() {
		return repos.findAll();
	}
	@Override
	public admin getadminById(int admin_id) {	
		return repos.findById(admin_id).orElseThrow(()-> new IdNotValidException("sourcefield","sourcevalue",admin_id));
	}
	@Override
	public admin updateadmin(admin obj, int admin_id) {	
		admin obj1=repos.findById(admin_id).orElseThrow(()-> new IdNotValidException("sourcefield","sourcevalue",admin_id));
		obj1.setAdmin_id(admin_id);
		obj1.setAdmin_name(obj.getAdmin_name());
		obj1.setPassword(obj.getPassword());
		admin save=repos.save(obj);
		return save ;
	}

	@Override
	public void deleteadmin(int admin_id) {
		admin obj2=repos.findById(admin_id).orElseThrow(()-> new IdNotValidException("sourcefield","sourcevalue",admin_id));
		repos.delete(obj2);			
	}
	@Override
	public int loginvalidation(String username, String Password) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement stm = connection.prepareStatement("SELECT * FROM Admin WHERE admin_name='"
					+username+"'");
			ResultSet res=stm.executeQuery();
			while(res.next()) {
				if(res.getString(2).equals(username) && res.getString(3).equals(Password)) {
				flag=1;
			}else {
				System.out.println("Invalid");
				flag=0;
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	
}

