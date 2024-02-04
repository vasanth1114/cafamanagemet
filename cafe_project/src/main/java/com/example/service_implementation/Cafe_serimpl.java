package com.example.service_implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dbutil.Dbutil;
import com.example.entity.Cafe;
import com.example.entity.Cartuser;
import com.example.exception.IdNotValidException;
import com.example.repository.Cafe_repository;
import com.example.service.Cafe_service;

@Service
public class Cafe_serimpl implements Cafe_service{
	@Autowired
	private Cafe_repository repo;
	Connection connection;
	

	public Cafe_serimpl(Cafe_repository repo) throws SQLException {
		super();
		this.repo = repo;
		connection=Dbutil.getConnection();
	}


	@Override
	public Cafe saveitems(Cafe item) {
		
		return repo.save(item);
	}
	//-----------------------------view all---------------------------------------
	@Override
	public List<Cafe> getAllmenu() {

		return repo.findAll();
	}
	//--------------------------View By Id-------------------------------------------------
	@Override
	public Cafe getmenuById(int dish_id) {

		return repo.findById(dish_id).orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", dish_id));
	}
	//--------------------------------update---------------------------------------------------------------
	@Override
	public Cafe updatemenu(Cafe item, int dish_id) {

		Cafe obj = repo.findById(dish_id)
				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", dish_id));
		obj.setDish_id(dish_id);
		obj.setDish_name(item.getDish_name());
		obj.setPrice(item.getPrice());
		Cafe save = repo.save(obj);
		return save;
	}
	//--------------------------------Delete---------------------------------------------------------
	@Override
	public void deletemenu(int dish_id) {
		Cafe obj1 = repo.findById(dish_id)
				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", dish_id));
		repo.delete(obj1);
	}


	@Override
	public List<Cafe> getdishids(List<Integer> dishids) {
		    List<Cafe> dishList = new ArrayList<>();
		    try {
		        PreparedStatement stm = connection.prepareStatement("SELECT * FROM menu_items WHERE dish_id in"+
		        		dishids+"");
		        //stm.setInt(1, userId);
		        ResultSet resultSet = stm.executeQuery();

		        	while(resultSet.next()) {

		        	Cafe cafe = mapResultSetToCartuser(resultSet);
		        	dishList.add(cafe);
		        	}
		        

		        // Make sure to close the ResultSet and PreparedStatement
		        resultSet.close();
		        stm.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return dishList;
		}

		private Cafe mapResultSetToCartuser(ResultSet resultSet) throws SQLException {
		    Cafe cafe = new Cafe();
		    
		    // Assuming Cartuser has appropriate setters
		    cafe.setDish_id(resultSet.getInt("dish_id"));
		    cafe.setDish_name(resultSet.getString("dish_name"));
		    cafe.setPrice(resultSet.getInt("price"));
		    //cartuser.setOtherProperty(resultSet.getString("other_property"));
		    // Set other properties as needed

		    return cafe;

	}
}
