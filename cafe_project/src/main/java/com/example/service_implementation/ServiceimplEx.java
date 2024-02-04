package com.example.service_implementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dbutil.Dbutil;
import com.example.entity.EntityEx;
import com.example.exception.IdNotValidException;
import com.example.repository.RepositoryEx;
import com.example.repository.admin_repos;
import com.example.service.ServiceEx;
import jakarta.persistence.Entity;
@Service
public  class ServiceimplEx implements ServiceEx{
	
	@Autowired
	private  RepositoryEx repo;
	 Connection connection;
	 
	
public ServiceimplEx(RepositoryEx repo) throws SQLException {
		super();
		this.repo=repo;
		connection=Dbutil.getConnection();
	}

	public EntityEx savename(EntityEx item) {
		
		 return repo.save(item);
		}
	
	@Override
	public List<EntityEx> UserAllmenu() {
		
		return repo.findAll();
	}

	@Override
	public EntityEx UserId(int Userid) {
		
		return repo.findById(Userid).orElseThrow( () ->new IdNotValidException("sourcefield","sourcevalue",Userid));
	}

	
	
@Override
public EntityEx Userupdate(EntityEx item, int Userid) {

	EntityEx obj = repo.findById(Userid)
			.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", Userid));
	obj.setUserid(Userid);
	obj.setUsername(item.getUsername());
	obj.setEmail(item.getEmail());
	obj.setPassword(item.getPassword());
	obj.setPhoneno(item.getPhoneno());
	obj.setCity(item.getCity());
	EntityEx save=repo.save(obj);
	return save;
	
	}

@Override
public void UserDelete(int Userid ) {
	EntityEx obj1 = repo.findById(Userid)
			.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", Userid));
	repo.delete(obj1);
}
//----validation
public int Uloginvalidation(String username, String Password) {
	// TODO Auto-generated method stub
	int flag=0;
	try {
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM user_details WHERE username='"
				+username+"'");
		ResultSet res=stm.executeQuery();
		while(res.next()) {
			if(res.getString(6).equals(username) && res.getString(4).equals(Password)) {
				String str =res.getString(6);
				int n =res.getInt(1);
				flag=n;
				
				return flag;
				
		}else {
			flag = 0;
			System.out.println("Invalid");
			
		}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}


}