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
import com.example.entity.EntityEx;
import com.example.exception.IdNotValidException;
import com.example.repository.Cafe_repository;
import com.example.repository.Cartuserrepo;
import com.example.service.Cafe_service;
import com.example.service.Cart_service;
@Service
public class Cart_serimpl implements Cart_service{
	@Autowired
	private Cartuserrepo repo;
	Connection connection;
	

	public Cart_serimpl(Cartuserrepo repo) throws SQLException {
		super();
		this.repo = repo;
		connection=Dbutil.getConnection();
	}


	@Override
	public Cartuser savecart(Cartuser values) {
		
		return repo.save(values);
	}
	//-----------------------------view all---------------------------------------
	@Override
	public List<Cartuser> getAllcart() {
		 Cartuser res = null;
		    List<Cartuser> cartList = new ArrayList<>();
		    try {
		    	String str = "isordered";
		        PreparedStatement stm = connection.prepareStatement("SELECT * FROM cart_table WHERE isordered=?");
		        stm.setString(1, str);
		        ResultSet resultSet = stm.executeQuery();

		        	while(resultSet.next()) {

		        	Cartuser cartuser = mapResultSetCartuser(resultSet);
		            cartList.add(cartuser);
		        	}
		        

		        // Make sure to close the ResultSet and PreparedStatement
		        resultSet.close();
		        stm.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return cartList;
		}

	private Cartuser mapResultSetCartuser(ResultSet resultSet) throws SQLException {
	    Cartuser cartuser = new Cartuser();
	    
	    // Assuming Cartuser has appropriate setters
	    cartuser.setUserid(resultSet.getInt("userid"));
	    cartuser.setDishid(resultSet.getInt("dish_id"));
	    cartuser.setCartId(resultSet.getInt("cart_id"));
	    cartuser.setQuantity(resultSet.getInt("quantity"));
	    cartuser.setIsordered(resultSet.getString("isordered"));
	    //cartuser.setOtherProperty(resultSet.getString("other_property"));
	    // Set other properties as needed

	    return cartuser;
	}
	//--------------------------View By Id-------------------------------------------------
	@Override
	public List<Cartuser> getCartById(int userId) {
	    Cartuser res = null;
	    List<Cartuser> cartList = new ArrayList<>();
	    try {
	        PreparedStatement stm = connection.prepareStatement("SELECT * FROM cart_table WHERE userid="+
	        		userId+"");
	        //stm.setInt(1, userId);
	        ResultSet resultSet = stm.executeQuery();

	        	while(resultSet.next()) {

	        	Cartuser cartuser = mapResultSetToCartuser(resultSet);
	            cartList.add(cartuser);
	        	}
	        

	        // Make sure to close the ResultSet and PreparedStatement
	        resultSet.close();
	        stm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return cartList;
	}

	private Cartuser mapResultSetToCartuser(ResultSet resultSet) throws SQLException {
	    Cartuser cartuser = new Cartuser();
	    
	    // Assuming Cartuser has appropriate setters
	    cartuser.setUserid(resultSet.getInt("userid"));
	    cartuser.setDishid(resultSet.getInt("dish_id"));
	    cartuser.setCartId(resultSet.getInt("cart_id"));
	    cartuser.setQuantity(resultSet.getInt("quantity"));
	    //cartuser.setOtherProperty(resultSet.getString("other_property"));
	    // Set other properties as needed

	    return cartuser;
	}
//--------------------------------------------------------------------------------------------------
	public int ordercart(int userId) {
	    try {
	        String str = "isordered";
	        int n = userId;
	        
	        // Use a PreparedStatement with placeholders and set parameters
	        PreparedStatement stm = connection.prepareStatement("UPDATE cart_table SET isordered = ? WHERE userid = ?");
	        stm.setString(1, str);  // Assuming isordered is a string column
	        stm.setInt(2, n);

	        // Use executeUpdate for UPDATE statements
	        int rowCount = stm.executeUpdate();
	        
	        stm.close();
	        
	        // Optionally, check the number of rows affected
	        System.out.println("Rows affected: " + rowCount);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 1;
	}
	//--------------------------------update---------------------------------------------------------------
	@Override
	public Cartuser updatemenu(Cartuser values, int cart_id) {

//		Cafe obj = repo.findById(cart_id)
//				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", cart_id));
//		obj.setCart_id(cart_id);
//		obj.setDish_name(values.get());
//		obj.setPrice(values.getPrice());
//		Cafe save = repo.save(obj);
		return null;
	}
	//--------------------------------Delete---------------------------------------------------------
	@Override
	public void deletecart(int cart_id) {
		Cartuser obj1 = repo.findById(cart_id)
				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", cart_id));
		repo.delete(obj1);
	}
//	@Override
//	public Cartuser Orderupdate(Cartuser item, int cartId) {
//
//		Cartuser obj = repo.findById(cartId)
//				.orElseThrow(() -> new IdNotValidException("sourcefield", "sourcevalue", cartId));
//		obj.setIsordered("isordered");
//		Cartuser save=repo.save(obj);
//		return save;
//		
//		}

}
